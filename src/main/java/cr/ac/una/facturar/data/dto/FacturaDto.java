package cr.ac.una.facturar.data.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.Factura}
 */
@Value
@Builder
public class FacturaDto implements Serializable {
    Long id;
    LocalDateTime date;
    Long infoComercialId;
    String clientId;
    List<Long> productosId;
    Double iva;
    Long costoTotal;
    Long facturaProductoCantidadId;
}