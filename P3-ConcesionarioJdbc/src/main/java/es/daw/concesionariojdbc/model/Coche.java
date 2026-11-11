package es.daw.concesionariojdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fila de la tabla COCHE.
 * En la tabla hay una columna codigo_marca (FK); en Java guardamos el objeto Marca completo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coche {

    private Integer codigo;
    private String nombre;
    private double precio;
    private Marca marca;
}
