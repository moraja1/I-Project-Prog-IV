package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.CuentaDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Cuenta;

import java.util.Optional;

public interface CuentaService {
    ProveedorDto addCuentaToProv(PersonaDto prov);

    CuentaDto findById(Long id);

    Cuenta findCuentaById(Long cuentaId);
}
