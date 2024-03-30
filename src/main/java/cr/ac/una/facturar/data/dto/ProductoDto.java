package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Producto;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Producto}
 */
@Data
@Builder
public class ProductoDto implements Serializable {
    Long costo;
    String descripcion;
}