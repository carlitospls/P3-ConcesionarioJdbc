package es.daw.concesionariojdbc.dto;

import es.daw.concesionariojdbc.model.Marca;
import es.daw.concesionariojdbc.model.Coche;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto del formulario de coche (viaja entre la vista y el controlador).
 * El <select> solo envía el código de la marca, por eso aquí es un Integer.
 */
@Data
@NoArgsConstructor
public class CocheForm {

    private Integer codigo;            // null = alta, con valor = edición

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    @NotNull(message = "Selecciona una marca")
    private Integer codigoMarca;

    /** Coche (modelo) → formulario. Se usa al editar. */
    public static CocheForm toDto(Coche p) {
        CocheForm form = new CocheForm();
        form.setCodigo(p.getCodigo());
        form.setNombre(p.getNombre());
        form.setPrecio(p.getPrecio());
        form.setCodigoMarca(p.getMarca().getCodigo());
        return form;
    }

    /** Formulario → Coche (modelo). De la marca solo tenemos el código. */
    public Coche toModelEntity() {
        Marca marca = new Marca(codigoMarca, null);
        return new Coche(codigo, nombre, precio, marca);
    }
}
