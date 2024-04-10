package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    public static PersonaDto mapPersonaToPersonaDto(Persona persona) {
        return PersonaDto.builder()
                .id(persona.getId())
                .name(persona.getName())
                .lastName(persona.getLastName())
                .phoneNumber(persona.getPhoneNumber())
                .email(persona.getEmail())
                .dtype(persona.getDtype())
                .build();
    }

    public static ProveedorDto mapPersonaDtoToProveedorDto(PersonaDto person) {
        return ProveedorDto.builder()
                .id(person.id())
                .name(person.name())
                .lastName(person.lastName())
                .phoneNumber(person.phoneNumber())
                .email(person.email())
                .pass(person.pass())
                .cuentaId(null)
                .autorizado(false)
                .build();
    }
}
