package es.daw.concesionariojdbc.repository;

import es.daw.concesionariojdbc.model.Coche;

/** Repositorio de coches: CRUD genérico + consultas propias. */
public interface CocheRepository extends GenericRepository<Coche, Integer> {

    /** ¿Hay algún coche de esta marca? */
    boolean existsByMarcaCodigo(Integer codigoMarca);
}
