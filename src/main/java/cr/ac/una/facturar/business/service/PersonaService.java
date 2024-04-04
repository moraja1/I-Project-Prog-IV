package cr.ac.una.facturar.business.service;


import cr.ac.una.facturar.data.dto.PersonaDto;

import java.util.List;

public interface PersonaService {
    PersonaDto userHasAccess(String email, String password);

    List<PersonaDto> findAllRegisteredUsers();

    boolean saveProveedor(PersonaDto person);
}
