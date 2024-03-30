package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.dto.InformacionComercialDto;
import cr.ac.una.facturar.data.entities.Cliente;
import cr.ac.una.facturar.data.entities.TiposCedula;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */
@Data
@Builder
public class ClienteDto implements Serializable {
    String id;
    String name;
    String lastName;
    String phoneNumber;
    String email;
    TiposCedula tipoId;
    InformacionComercialDto infoComercial;
}