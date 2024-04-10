package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.FacturaService;
import cr.ac.una.facturar.data.dto.FacturaDto;
import cr.ac.una.facturar.data.entities.Factura;
import cr.ac.una.facturar.business.mappers.FacturaMapper;
import cr.ac.una.facturar.data.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {
    private final FacturaRepository facturaRepository;

    @Autowired
    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public FacturaDto findById(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);

        if(factura.isEmpty()) return null;

        return FacturaMapper.mapFacturaToFacturaDto(factura.get());
    }
}
