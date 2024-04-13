package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.ProductoMapper;
import cr.ac.una.facturar.business.service.ProductoService;
import cr.ac.una.facturar.data.dto.ProductoDto;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Producto;
import cr.ac.una.facturar.data.repository.CuentaRepository;
import cr.ac.una.facturar.data.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository PR;
    @Autowired
    public ProductoServiceImpl(ProductoRepository PR, CuentaRepository CR) {
        this.PR = PR;
    }

    @Override
    public ProductoDto findById(Long id) {
        Optional<Producto> persistedProv = PR.findById(id);
        return persistedProv.map(ProductoMapper::mapProductoToProductoDto).orElse(null);
    }

    @Override
    public boolean save(ProductoDto producto) {
        //Save
        Optional<Producto> persisted = PR.findById(producto.getId());
        if(persisted.isEmpty()) {
            Producto toSave = ProductoMapper.mapProductoDtoToProducto(producto);
            PR.save(toSave);
            return true;
        }

        //Si ya existe
        Producto persistedEntity = persisted.get();
        updateEntity(persistedEntity, producto);
        PR.save(persistedEntity);
        return true;
    }

    @Override
    public Producto findProductoById(Long id) {
        Optional<Producto> producto = PR.findById(id);
        return producto.orElse(null);
    }

    @Override
    public Producto save(Producto p) {
        p = PR.save(p);
        return p;
    }

    @Override
    public List<Producto> findAllByCuenta(Cuenta c) {
        return PR.findAllByCuenta(c);
    }

    private void updateEntity(Producto entity, ProductoDto data) {
        entity.setCosto(data.getCosto());
        entity.setDescripcion(data.getDescripcion());
    }


}
