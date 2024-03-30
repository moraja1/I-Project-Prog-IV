package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.dto.DireccionDto;
import cr.ac.una.facturar.data.entities.InformacionComercial;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link InformacionComercial}
 */
@Data
@Builder
public class InformacionComercialDto implements Serializable {
    String razonSocial;
    String nombre;
    DireccionDto direccion;
}