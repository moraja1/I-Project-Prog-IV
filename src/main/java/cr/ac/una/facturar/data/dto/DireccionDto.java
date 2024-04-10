package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Direccion;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Direccion}
 */
@Value
@Builder
public class DireccionDto implements Serializable {
    Long id;
    String provincia;
    String canton;
    String distrito;
}