package es.daw.concesionariojdbc.service;

import es.daw.concesionariojdbc.exception.RecursoNoEncontradoException;
import es.daw.concesionariojdbc.model.Marca;
import es.daw.concesionariojdbc.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    public Marca buscarPorCodigo(Integer codigo) {
        return marcaRepository.findById(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el fabricante con código " + codigo));
    }


}
