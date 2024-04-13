package cr.ac.una.facturar.data.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.FacturaProductoCantidad}
 */

@Builder
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class FacturaProductoCantidadDto implements Serializable {
    private Long productoId;
    private ProductoDto productoDto;
    private Long cantidad;
    private Long costo;

}