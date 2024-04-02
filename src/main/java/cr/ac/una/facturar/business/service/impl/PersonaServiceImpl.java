package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.PersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Override
    public boolean userHasAccess(String email, String password) {
        return false;
    }
}
