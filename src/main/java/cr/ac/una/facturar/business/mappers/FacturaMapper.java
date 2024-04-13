package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.FacturaDto;
import cr.ac.una.facturar.data.entities.Factura;
import cr.ac.una.facturar.data.entities.Producto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacturaMapper {

    public static FacturaDto mapFacturaToFacturaDto(Factura factura) {;

        return FacturaDto.builder()
                .id(factura.getId())
                .date(factura.getDate())
                .clientId(factura.getClientInfo().getId())
                .costoTotal(factura.getCostoTotal())
                .build();
    }
}
