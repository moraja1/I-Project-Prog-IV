package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Cuenta;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.Proveedor}
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDto implements Serializable {
    String id;
    String name;
    String lastName;
    String phoneNumber;
    String email;
    String pass;
    String dtype;
    String direccion;;
    Boolean autorizado;
    Long cuentaId;
}