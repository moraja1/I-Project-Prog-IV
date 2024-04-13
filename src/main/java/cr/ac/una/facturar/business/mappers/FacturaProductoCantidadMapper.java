package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.FacturaProductoCantidadDto;
import cr.ac.una.facturar.data.entities.FacturaProductoCantidad;
import cr.ac.una.facturar.data.entities.Producto;

public class FacturaProductoCantidadMapper {
    public static FacturaProductoCantidad mapDtoToFacturaProductoCantidad(
            FacturaProductoCantidadDto facturaProductoCantidadDto,
            Producto producto){

        return FacturaProductoCantidad.builder()
                .producto(producto)
                .cantidad(facturaProductoCantidadDto.getCantidad())
                .costo(facturaProductoCantidadDto.getCosto())
                .build();
    }
}
