package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.FacturaProductoCantidadMapper;
import cr.ac.una.facturar.business.service.ClienteService;
import cr.ac.una.facturar.business.service.FacturaService;
import cr.ac.una.facturar.data.dto.FacturaDto;
import cr.ac.una.facturar.data.dto.FacturaProductoCantidadDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.*;
import cr.ac.una.facturar.business.mappers.FacturaMapper;
import cr.ac.una.facturar.data.repository.CuentaRepository;
import cr.ac.una.facturar.data.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cr.ac.una.facturar.business.mappers.FacturaProductoCantidadMapper.mapDtoToFacturaProductoCantidad;
import static java.util.Arrays.stream;

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
        return factura.map(FacturaMapper::mapFacturaToFacturaDto).orElse(null);

    }

    @Override
    public List<Factura> findAllByCuenta(Cuenta c) {
        return facturaRepository.findAllByCuenta(c);
    }

    @Override
    public FacturaDto joinFacturaComponents(PersonaDto clientSelected,
                                            List<FacturaProductoCantidadDto> productsSelected,
                                            ProveedorDto proveedorDto) {
        Long costoTotal = 0L;
        for(var x : productsSelected) {
            costoTotal += x.getCosto();
        }

        return FacturaDto.builder()
                .clientId(clientSelected.id())
                .facturaProductoCantidad(productsSelected)
                .cuentaId(proveedorDto.getCuentaId())
                .costoTotal(costoTotal)
                .build();
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }


}
