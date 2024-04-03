package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.entities.Persona;
import cr.ac.una.facturar.data.entities.Proveedor;
import cr.ac.una.facturar.data.repository.PersonaRepository;
import cr.ac.una.facturar.data.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;
    private final ProveedorRepository proveedorRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository, ProveedorRepository proveedorRepository) {
        this.personaRepository = personaRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public PersonaDto userHasAccess(String email, String password) {
        Optional<Persona> persona = personaRepository.findByEmailAndPass(email, password);
        //Person is not registered
        if(persona.isEmpty()) return null;

        //Person is Admin
        if(persona.get().getDtype().equals("Admin")) return persona.map(this::mapToDto).orElse(null);

        //Person is proveedor
        String id = persona.get().getId();
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);

        //Asserting while testing app
        assert(proveedor.isPresent());

        //Proveedor authorized
        if(proveedor.get().getAutorizado()) return persona.map(this::mapToDto).orElse(null);

        //Proveedor not authorized
        return null;
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
