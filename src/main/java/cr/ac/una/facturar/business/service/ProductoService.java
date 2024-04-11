package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.ProductoDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Producto;
import cr.ac.una.facturar.data.entities.Proveedor;

import java.util.List;

public interface ProductoService {
    ProductoDto findById(Long id);

    boolean save(ProductoDto producto);

    Producto findProductoById(Long id);

    Producto save(Producto p);

}
