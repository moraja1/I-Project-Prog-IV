package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.entities.Persona;
import cr.ac.una.facturar.data.entities.Proveedor;
import cr.ac.una.facturar.data.repository.PersonaRepository;
import cr.ac.una.facturar.data.repository.ProveedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
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
        if(persona.get().getDtype().equals("Admin")) return persona.map(this::mapPersonaToDto).orElse(null);

        //Person is proveedor
        String id = persona.get().getId();
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);

        //Asserting while testing app
        assert(proveedor.isPresent());

        //Proveedor authorized
        if(proveedor.get().getAutorizado()) return persona.map(this::mapPersonaToDto).orElse(null);

        //Proveedor not authorized
        return null;
    }

    @Override
    public List<PersonaDto> findAllRegisteredUsers() {
        List<Proveedor> proveedores = proveedorRepository.findAllByAutorizado(true);

        //No authorized suppliers
        if(proveedores.isEmpty()) return new ArrayList<>();

        //returning registered suppliers
        return proveedores.stream().map(this::mapProveedorToDto).toList();
    }

    @Override
    public boolean saveProveedor(PersonaDto person) {
        //External request to ministry
        String uri = "http://localhost:8080/ministryStub/api/user/207930197";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        //Validation of request to Ministry
        boolean isRegisteredInMinistry = responseEntity.getStatusCode().equals(HttpStatus.OK);
        if(!isRegisteredInMinistry) return false;

        //Saving proveedor
        Proveedor proveedor = mapDtoRegistrationToProveedor(person);
        Proveedor savedProveedor = proveedorRepository.save(proveedor);

        return proveedor.equals(savedProveedor);
    }

    private Proveedor mapDtoRegistrationToProveedor(PersonaDto person) {
        return Proveedor.builder()
                .id(person.id())
                .name(person.name())
                .lastName(person.lastName())
                .phoneNumber(person.phoneNumber())
                .email(person.email())
                .pass(person.pass())
                .infoComercial(null)
                .cuenta(null)
                .autorizado(false)
                .build();
    }


    private PersonaDto mapProveedorToDto(Proveedor proveedor) {
        return PersonaDto.builder()
                .id(proveedor.getId())
                .name(proveedor.getName())
                .lastName(proveedor.getLastName())
                .build();
    }

    private PersonaDto mapPersonaToDto(Persona persona) {
        return PersonaDto.builder()
                .id(persona.getId())
                .name(persona.getName())
                .lastName(persona.getLastName())
                .phoneNumber(persona.getPhoneNumber())
                .email(persona.getEmail())
                .dtype(persona.getDtype())
                .build();
    }
}
