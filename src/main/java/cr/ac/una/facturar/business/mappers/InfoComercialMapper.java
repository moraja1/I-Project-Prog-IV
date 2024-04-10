package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.InformacionComercialDto;
import cr.ac.una.facturar.data.entities.InformacionComercial;
import org.springframework.stereotype.Component;

@Component
public class InfoComercialMapper {
    public static InformacionComercialDto mapInfoComercialToInfoComercialDto(InformacionComercial infoComercial){
        return InformacionComercialDto.builder()
                .id(infoComercial.getId())
                .nombre(infoComercial.getNombre())
                .razonSocial(infoComercial.getRazonSocial())
                .direccionId(infoComercial.getDireccion().getId())
                .build();
    }
}
