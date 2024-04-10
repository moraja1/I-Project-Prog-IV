package cr.ac.una.facturar.data.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.Cliente}
 */
@Value
@Builder
public class ClienteDto implements Serializable {
    String id;
    String name;
    String lastName;
    String phoneNumber;
    String email;
    String dtype;
    Long infoComercialId;
}