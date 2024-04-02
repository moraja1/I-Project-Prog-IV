package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.entities.Persona;
import cr.ac.una.facturar.data.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaDto userHasAccess(String email, String password) {
        Optional<Persona> persona = personaRepository.findByEmailAndPass(email, password);
        return persona.map(this::mapToDto).orElse(null);
    }

    private PersonaDto mapToDto(Persona persona) {
        return PersonaDto.builder()
                .id(persona.getId())
                .tipoId(persona.getTipoId())
                .name(persona.getName())
                .lastName(persona.getLastName())
                .phoneNumber(persona.getPhoneNumber())
                .email(persona.getEmail())
                .dtype(persona.getDtype())
                .build();
    }
}
