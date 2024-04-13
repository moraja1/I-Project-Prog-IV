package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.FacturaDto;
import cr.ac.una.facturar.data.entities.Cliente;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Factura;
import cr.ac.una.facturar.data.entities.FacturaProductoCantidad;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FacturaMapper {

    public static FacturaDto mapFacturaToFacturaDto(Factura factura) {;

        return FacturaDto.builder()
                .id(factura.getId())
                .date(factura.getDate())
                .clientId(factura.getClientInfo().getId())
                .facturaProductoCantidad(new ArrayList<>())
                .costoTotal(factura.getCostoTotal())
                .build();
    }

    public static Factura mapFacturaDtoToFactura(FacturaDto facturaDto, Cliente cliente) {
        return Factura.builder()
                .date(facturaDto.getDate())
                .clientInfo(cliente)
                .costoTotal(facturaDto.getCostoTotal())
                .productoCantidad(new ArrayList<>())
                .build();
    }
}
