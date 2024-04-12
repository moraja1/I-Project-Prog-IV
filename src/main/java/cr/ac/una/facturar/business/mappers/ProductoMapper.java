package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.ProductoDto;
import cr.ac.una.facturar.data.entities.Producto;

public class ProductoMapper {
    public static ProductoDto mapProductoToProductoDto(Producto producto) {
        return ProductoDto.builder()
                .id(producto.getId())
                .costo(producto.getCosto())
                .descripcion(producto.getDescripcion())
                .build();
    }

    public static Producto mapProductoDtoToProducto(ProductoDto producto) {
        return Producto.builder()
                .id(producto.getId())
                .costo(producto.getCosto())
                .descripcion(producto.getDescripcion())
                .build();
    }
}
