package cr.ac.una.facturar.data.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.InformacionComercial}
 */
@Value
@Builder
public class InformacionComercialDto implements Serializable {
    Long id;
    String razonSocial;
    String nombre;
    String provincia;
    String canton;
    String distrito;

}