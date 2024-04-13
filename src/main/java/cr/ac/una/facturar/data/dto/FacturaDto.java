package cr.ac.una.facturar.data.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.Factura}
 */

@Builder
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class FacturaDto implements Serializable {
    private Long id;
    private Date date;
    private String clientId;
    private Long cuentaId;
    private Long costoTotal;
    private List<FacturaProductoCantidadDto> facturaProductoCantidad;
}