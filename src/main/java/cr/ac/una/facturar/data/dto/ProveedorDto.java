package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Proveedor;
import cr.ac.una.facturar.data.entities.TiposCedula;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Proveedor}
 */
@Data
@Builder
public class ProveedorDto implements Serializable {
    private String id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private TiposCedula tipoId;
    private String username;
    private Boolean estaAutorizado;
}