package es.daw.concesionariojdbc.repository;

import es.daw.concesionariojdbc.exception.AccesoDatosException;
import es.daw.concesionariojdbc.model.Marca;
import es.daw.concesionariojdbc.model.Coche;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Capa de datos: la única que escribe SQL.
@Repository
@RequiredArgsConstructor
public class CocheRepositoryJdbc implements CocheRepository {

    // SELECT común a findById y findAll (JOIN para traer también el nombre de la marca)
    private static final String SELECT_BASE = """
            SELECT co.codigo, co.nombre, co.precio,
                   m.codigo AS m_codigo, m.nombre AS m_nombre
            FROM coche co
            JOIN marca m ON co.codigo_marca = m.codigo
            """;

    private final DataSource dataSource;

    @Override
    public Optional<Coche> findById(Integer codigo) {
        String sql = SELECT_BASE + "WHERE co.codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar el coche " + codigo, e);
        }
    }

    @Override
    public List<Coche> findAll() {
        String sql = SELECT_BASE + "ORDER BY co.codigo";
        List<Coche> coches = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                coches.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar los coches", e);
        }
        return coches;
    }

    @Override
    public void save(Coche coche) {
        String sql = "INSERT INTO coche (nombre, precio, codigo_marca) VALUES (?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, coche.getNombre());
            ps.setDouble(2, coche.getPrecio());
            ps.setInt(3, coche.getMarca().getCodigo());
            ps.executeUpdate();
            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    coche.setCodigo(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al guardar el coche", e);
        }
    }

    @Override
    public void update(Coche coche) {
        String sql = "UPDATE coche SET nombre = ?, precio = ?, codigo_marca = ? WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, coche.getNombre());
            ps.setDouble(2, coche.getPrecio());
            ps.setInt(3, coche.getMarca().getCodigo());
            ps.setInt(4, coche.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al actualizar el coche " + coche.getCodigo(), e);
        }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "DELETE FROM coche WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al borrar el coche " + codigo, e);
        }
    }

    //
    @Override
    public boolean existsByMarcaCodigo(Integer codigoMarca) {
        String sql = "SELECT 1 FROM coche WHERE codigo_marca = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoMarca);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al comprobar los coches de la marca " + codigoMarca, e);
        }
    }


    // Fila del ResultSet → objeto Coche
    private Coche mapRow(ResultSet rs) throws SQLException {
        Marca marca = new Marca(
                rs.getInt("m_codigo"),
                rs.getString("m_nombre"));

        return new Coche(
                rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                marca);
    }
}
