package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.PersonaMapper;
import cr.ac.una.facturar.business.mappers.ProveedorMapper;
import cr.ac.una.facturar.business.service.PersonaService;
import cr.ac.una.facturar.business.service.ProveedorService;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Persona;
import cr.ac.una.facturar.data.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cr.ac.una.facturar.business.mappers.PersonaMapper.mapPersonaDtoToProveedorDto;
import static cr.ac.una.facturar.business.mappers.PersonaMapper.mapPersonaToPersonaDto;
import static cr.ac.una.facturar.business.mappers.ProveedorMapper.*;

@Service
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;
    private final ProveedorService proveedorService;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository, ProveedorService proveedorService) {
        this.personaRepository = personaRepository;
        this.proveedorService = proveedorService;
    }

    @Override
    public PersonaDto userHasAccess(String email, String password) {
        Optional<Persona> persona = personaRepository.findByEmailAndPass(email, password);
        //Person is not registered
        if(persona.isEmpty()) return null;

        //Person is Admin
        if(persona.get().getDtype().equals("Admin")) return persona.map(PersonaMapper::mapPersonaToPersonaDto).orElse(null);

        //Person is proveedor
        String id = persona.get().getId();
        ProveedorDto proveedor = proveedorService.findById(id);

        //Proveedor is not loggin in
        if(proveedor == null) return null;

        //Proveedor authorized
        if(proveedor.getAutorizado()) return persona.map(PersonaMapper::mapPersonaToPersonaDto).orElse(null);

        //Proveedor not authorized
        return null;
    }

    @Override
    public List<PersonaDto> findAllAuthorizedProvs() {
        List<ProveedorDto> proveedores = proveedorService.findAllByAutorizado(true);

        //No authorized suppliers
        if(proveedores.isEmpty()) return new ArrayList<>();

        //returning registered suppliers
        return proveedores.stream().map(ProveedorMapper::mapProveedorDtoToPersonaDto).toList();
    }

    @Override
    public boolean saveProveedor(PersonaDto person) {
        //Actualmente registrado
        if(personaRepository.existsById(person.id())) return false;

        //External request to ministry
        String uri = String.format("http://localhost:8080/api/user/%s", person.id());
        RestTemplate restTemplate = new RestTemplate();
        Boolean isRegisteredInMinistry = restTemplate.getForObject(uri, Boolean.class);
        //Validation of request to Ministry
        if(Boolean.FALSE.equals(isRegisteredInMinistry)) return false;

        //Saving proveedor
        ProveedorDto proveedor = mapPersonaDtoToProveedorDto(person);
        proveedorService.save(proveedor);
        return true;
    }

    @Override
    public PersonaDto updatePersonProfile(String id, PersonaDto user) {
        Optional<Persona> p = personaRepository.findById(id);

        //Id does not correspond to DB
        if(p.isEmpty()) return null;

        //Proceed to update info
        Persona persisted = p.get();
        persisted.setPhoneNumber(user.phoneNumber());
        persisted.setEmail(user.email());
        persisted.setName(user.name());
        persisted.setLastName(user.lastName());

        return mapPersonaToPersonaDto(persisted);
    }

    @Override
    public List<PersonaDto> findAllUnauthorizedProvs() {
        List<ProveedorDto> proveedores = proveedorService.findAllByAutorizado(false);

        //No authorized suppliers
        if(proveedores.isEmpty()) return new ArrayList<>();

        //returning registered suppliers
        return proveedores.stream().map(ProveedorMapper::mapProveedorDtoToPersonaDto).toList();
    }

    @Override
    public PersonaDto giveAccessToProv(String person) {
        ProveedorDto prov = proveedorService.findById(person);

        //Proveedor does not exist by any reason
        if(prov == null) return null;
        prov.setAutorizado(true);

        //Save Prov with account
        proveedorService.save(prov);

        //return dto
        return mapProveedorDtoToPersonaDto(prov);
    }


}
