package es.daw.concesionariojdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Fila de la tabla MARCA. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

    private Integer codigo;
    private String nombre;
}
