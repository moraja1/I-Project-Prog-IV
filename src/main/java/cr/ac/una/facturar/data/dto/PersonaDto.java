package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.entities.Persona;
import cr.ac.una.facturar.data.entities.TiposCedula;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link Persona}
 */
@Builder
public record PersonaDto(String id, TiposCedula tipoId, String name, String lastName, String phoneNumber, String email,
                         String dtype) implements Serializable {
}