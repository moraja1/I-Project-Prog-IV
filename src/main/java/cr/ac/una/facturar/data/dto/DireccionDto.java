package cr.ac.una.facturar.data.dto;

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