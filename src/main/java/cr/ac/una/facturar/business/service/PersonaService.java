package cr.ac.una.facturar.business.service;


import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;

import java.util.List;

public interface PersonaService {
    PersonaDto userHasAccess(String email, String password);

    List<PersonaDto> findAllRegisteredUsers();

    boolean saveProveedor(PersonaDto person);

    boolean updatePersonProfile(PersonaDto user);

    List<PersonaDto> findAllUnauthorizedProvs();
}
