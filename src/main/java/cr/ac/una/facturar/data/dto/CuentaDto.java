package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Factura;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.Cuenta}
 */
@Value
@Builder
public class CuentaDto implements Serializable {
    Long id;
    List<String> clientesId;
    List<Long> productosId;
    List<Long> facturasId;
}