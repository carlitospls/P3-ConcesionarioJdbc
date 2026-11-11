package es.daw.concesionariojdbc.service;

import es.daw.concesionariojdbc.exception.RecursoNoEncontradoException;
import es.daw.concesionariojdbc.model.Marca;
import es.daw.concesionariojdbc.model.Coche;
import es.daw.concesionariojdbc.repository.MarcaRepository;
import es.daw.concesionariojdbc.repository.CocheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Capa de lógica: reglas de negocio. No sabe nada de HTTP ni de SQL.
@Service
@RequiredArgsConstructor
public class CocheService {

    private final CocheRepository cocheRepository;
    private final MarcaRepository marcaRepository;

    public List<Coche> listar() {
        return cocheRepository.findAll();
    }

    public Coche buscarPorCodigo(Integer codigo) {
        return cocheRepository.findById(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el coche con código " + codigo));
    }

    public Coche crear(Coche coche) {
        prepararParaGuardar(coche);
        cocheRepository.save(coche);
        return coche;
    }

    public Coche actualizar(Coche coche) {
        buscarPorCodigo(coche.getCodigo());
        prepararParaGuardar(coche);
        cocheRepository.update(coche);
        return coche;
    }

    public void borrar(Integer codigo) {
        buscarPorCodigo(codigo);
        cocheRepository.delete(codigo);
    }

    public List<Coche> listarPorMarca(Integer codigoMarca) {

        // Regla de negocio: comprobar que el codigoFabricante existe...
        marcaRepository.findById(codigoMarca).orElseThrow(
                () -> new RecursoNoEncontradoException("No existe el fabricante con código "+ codigoMarca));

        return cocheRepository.findByMarcaCodigo(codigoMarca);
    }

    // Reglas comunes a crear y actualizar
    private void prepararParaGuardar(Coche coche) {
        coche.setNombre(coche.getNombre().trim());

        Integer codigoMarca = coche.getMarca().getCodigo();
        Marca marca = marcaRepository.findById(codigoMarca)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la marca con código " + codigoMarca));
        coche.setMarca(marca);
    }
}
