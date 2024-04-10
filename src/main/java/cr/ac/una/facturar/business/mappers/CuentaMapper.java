package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.CuentaDto;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Factura;
import cr.ac.una.facturar.data.entities.Persona;
import cr.ac.una.facturar.data.entities.Producto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CuentaMapper {
    public static CuentaDto mapCuentaToDto(Cuenta cuenta, List<String> clientesId, List<Long> productosId, List<Long> facturasId) {
        return CuentaDto.builder()
                .id(cuenta.getId())
                .clientesId(clientesId)
                .productosId(productosId)
                .facturasId(facturasId)
                .build();
    }
}
