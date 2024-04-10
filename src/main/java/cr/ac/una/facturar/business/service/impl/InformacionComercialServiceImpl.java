package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.InfoComercialMapper;
import cr.ac.una.facturar.business.service.InformacionComercialService;
import cr.ac.una.facturar.data.dto.InformacionComercialDto;
import cr.ac.una.facturar.data.entities.InformacionComercial;
import cr.ac.una.facturar.data.repository.InformacionComercialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InformacionComercialServiceImpl implements InformacionComercialService {
    private final InformacionComercialRepository infoComercialRepository;

    @Autowired
    public InformacionComercialServiceImpl(InformacionComercialRepository infoComercialRepository) {
        this.infoComercialRepository = infoComercialRepository;
    }

    @Override
    public InformacionComercialDto findById(Long infoComercialId) {
        Optional<InformacionComercial> infoComercial = infoComercialRepository.findById(infoComercialId);
        return infoComercial.map(InfoComercialMapper::mapInfoComercialToInfoComercialDto).orElse(null);
    }

    @Override
    public InformacionComercial findInfoComercialById(Long infoComercialId) {
        Optional<InformacionComercial> infoComercial = infoComercialRepository.findById(infoComercialId);
        return infoComercial.orElse(null);
    }
}
