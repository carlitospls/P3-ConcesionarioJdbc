package es.daw.concesionariojdbc.repository;

import es.daw.concesionariojdbc.exception.AccesoDatosException;
import es.daw.concesionariojdbc.model.Coche;
import es.daw.concesionariojdbc.model.Marca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MarcaRepositoryJdbc implements MarcaRepository {

    private final DataSource dataSource;

    @Override
    public Optional<Marca> findById(Integer codigo) {
        String sql = "SELECT codigo, nombre FROM marca WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar la marca " + codigo, e);
        }
    }

    @Override
    public List<Marca> findAll() {
        String sql = "SELECT codigo, nombre FROM marca ORDER BY nombre";
        List<Marca> marcas = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                marcas.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar las marcas", e);
        }
        return marcas;
    }

    @Override
    public void save(Marca marca) {
        String sql = "INSERT INTO marca (nombre) VALUES (?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, marca.getNombre());
            ps.executeUpdate();
            try (ResultSet claves = ps.getGeneratedKeys()) {
                if (claves.next()) {
                    marca.setCodigo(claves.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al guardar la marca", e);
        }
    }

    @Override
    public void update(Marca marca) {
        String sql = "UPDATE marca SET nombre = ? WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al actualizar la marca " + marca.getCodigo(), e);
        }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "DELETE FROM marca WHERE codigo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al borrar la marca " + codigo, e);
        }
    }

    private Marca mapRow(ResultSet rs) throws SQLException {
        return new Marca(rs.getInt("codigo"), rs.getString("nombre"));
    }



}
