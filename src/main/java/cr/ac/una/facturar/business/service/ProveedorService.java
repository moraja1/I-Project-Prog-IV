package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.ProveedorDto;

import java.util.List;

public interface ProveedorService {
    ProveedorDto findById(String id);

    List<ProveedorDto> findAllByAutorizado(boolean b);

    boolean save(ProveedorDto proveedor);
}
