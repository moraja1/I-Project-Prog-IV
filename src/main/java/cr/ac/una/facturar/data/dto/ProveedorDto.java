package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Proveedor;
import cr.ac.una.facturar.data.entities.TiposCedula;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Proveedor}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProveedorDto implements Serializable {
    String id;
    String name;
    String lastName;
    String phoneNumber;
    String email;
    TiposCedula tipoId;
    String username;
    Boolean estaAutorizado;
}