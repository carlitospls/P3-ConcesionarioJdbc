package es.daw.concesionariojdbc.repository;

import es.daw.concesionariojdbc.model.Coche;
import es.daw.concesionariojdbc.model.Marca;

import java.util.List;

public interface MarcaRepository extends GenericRepository<Marca, Integer> {

    List<Coche> findByMarcaCodigo(Integer codigoMarca);

}
