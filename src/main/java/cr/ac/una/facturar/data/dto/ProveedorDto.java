package cr.ac.una.facturar.data.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.Proveedor}
 */
@Builder
public class ProveedorDto implements Serializable {
    String id;
    String name;
    String lastName;
    String phoneNumber;
    String email;
    String pass;
    String dtype;
    Boolean autorizado;
}