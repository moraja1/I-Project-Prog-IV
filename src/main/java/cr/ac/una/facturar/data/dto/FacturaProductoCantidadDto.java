package cr.ac.una.facturar.data.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.FacturaProductoCantidad}
 */
@Value
public class FacturaProductoCantidadDto implements Serializable {
    Long id;
    Long productoIdId;
    Long cantidad;
}