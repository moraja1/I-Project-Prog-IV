package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Direccion;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Direccion}
 */
@Data
@Builder
public class DireccionDto implements Serializable {
    String provincia;
    String canton;
    String distrito;
}