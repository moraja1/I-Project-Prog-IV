package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Persona;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Persona}
 */
@Builder
public record PersonaDto(String id, String name, String lastName, String phoneNumber, String email,
                         String pass, String dtype, Long infoComercialId, String direccion) implements Serializable {
}