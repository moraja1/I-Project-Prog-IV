package cr.ac.una.facturar.business.service;


import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.presentacion.model.RegisteredUser;

import java.util.List;
import java.util.Optional;

public interface PersonaService {
    PersonaDto userHasAccess(String email, String password);

    List<RegisteredUser> findAllRegisteredUsers();
}
