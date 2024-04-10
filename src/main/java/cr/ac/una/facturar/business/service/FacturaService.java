package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.FacturaDto;

public interface FacturaService {
    FacturaDto findById(Long id);
}
