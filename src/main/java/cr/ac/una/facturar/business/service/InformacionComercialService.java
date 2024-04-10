package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.InformacionComercialDto;
import cr.ac.una.facturar.data.entities.InformacionComercial;

public interface InformacionComercialService {
    InformacionComercialDto findById(Long infoComercialId);

    InformacionComercial findInfoComercialById(Long infoComercialId);
}
