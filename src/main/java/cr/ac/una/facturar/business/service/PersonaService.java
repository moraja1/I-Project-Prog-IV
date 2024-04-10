package cr.ac.una.facturar.business.service;


import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.entities.Proveedor;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    PersonaDto userHasAccess(String email, String password);

    List<PersonaDto> findAllAuthorizedProvs();

    boolean saveProveedor(PersonaDto person);

    PersonaDto updatePersonProfile(String id, PersonaDto user);

    List<PersonaDto> findAllUnauthorizedProvs();

    PersonaDto giveAccessToProv(String person);


}
