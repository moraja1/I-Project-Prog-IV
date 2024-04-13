package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.FacturaDto;
import cr.ac.una.facturar.data.dto.FacturaProductoCantidadDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Factura;

import java.util.List;

public interface FacturaService {
    FacturaDto findById(Long id);
    List<Factura> findAllByCuenta(Cuenta c);

    FacturaDto joinFacturaComponents(PersonaDto clientSelected, List<FacturaProductoCantidadDto> productsSelected);
}
