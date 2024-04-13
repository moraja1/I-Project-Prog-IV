package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Producto;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Producto}
 */
@Value
@Builder
public class ProductoDto implements Serializable {
    Long id;
    Long costo;
    String descripcion;
}