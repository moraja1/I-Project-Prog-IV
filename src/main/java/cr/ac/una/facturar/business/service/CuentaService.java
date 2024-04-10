package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.*;
import cr.ac.una.facturar.data.entities.Cuenta;

import java.util.List;

public interface CuentaService {
    ProveedorDto addCuentaToProv(PersonaDto prov);

    CuentaDto findById(Long id);

    Cuenta findCuentaById(Long cuentaId);
    List<FacturaDto> findFacturaDtoList(Long id);
    List<PersonaDto> findClientesDtoList(Long cuentaId);
    List<ProductoDto> findProductosDtoList(Long cuentaId);
}
