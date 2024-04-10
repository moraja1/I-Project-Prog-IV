package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.*;
import cr.ac.una.facturar.business.service.CuentaService;
import cr.ac.una.facturar.business.service.ProveedorService;
import cr.ac.una.facturar.data.dto.*;
import cr.ac.una.facturar.data.entities.*;
import cr.ac.una.facturar.data.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cr.ac.una.facturar.business.mappers.CuentaMapper.mapCuentaToDto;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ProveedorService proveedorService;

    @Autowired
    public CuentaServiceImpl(CuentaRepository cuentaRepository, ProveedorService proveedorService) {
        this.cuentaRepository = cuentaRepository;
        this.proveedorService = proveedorService;
    }

    private Cuenta createsNewAccount() {
        //Creates new Acc
        Cuenta newCuenta = Cuenta.builder().build();

        //Persists acc
        newCuenta = cuentaRepository.save(newCuenta);

        return newCuenta;
    }

    @Override
    public ProveedorDto addCuentaToProv(PersonaDto prov) {
        //Find persisted prov
        Proveedor p = proveedorService.findProveedorById(prov.id());

        //Evaluates result
        if(p == null) return null;
        if(!(p.getDtype().equals("Proveedor"))) return null;

        //Creates new acc
        Cuenta c = createsNewAccount();

        //Assign Account
        p.setCuenta(c);
        p.setAutorizado(true);
        if(proveedorService.save(p) != null) return ProveedorMapper.mapProveedorToProveedorDto(p);

        return null;
    }

    @Override
    public CuentaDto findById(Long id) {
        //Find cuenta
        Optional<Cuenta> cuentaPersisted = cuentaRepository.findById(id);

        if(cuentaPersisted.isEmpty()) return null;

        Cuenta cuenta = cuentaPersisted.get();
        List<String> clientesId = cuenta.getClientes().stream().map(Persona::getId).toList();
        List<Long> productosId = cuenta.getProductos().stream().map(Producto::getId).toList();
        List<Long> facturasId = cuenta.getFacturas().stream().map(Factura::getId).toList();

        //Return Dto
        return mapCuentaToDto(cuenta, clientesId, productosId, facturasId);
    }

    @Override
    public Cuenta findCuentaById(Long id) {
        Optional<Cuenta> cuentaPersisted = cuentaRepository.findById(id);

        return cuentaPersisted.orElse(null);
    }

    @Override
    public List<FacturaDto> findFacturaDtoList(Long cuentaId) {
        Cuenta c = findCuentaById(cuentaId);

        //if there is no acc for any reason
        if(c == null) throw new RuntimeException("El proveedor no tiene cuenta. findFacturaDtoList en CuentaServiceImpl");

        List<Factura> facturas = c.getFacturas();
        if(facturas == null) return new ArrayList<>();
        return facturas.stream().map(FacturaMapper::mapFacturaToFacturaDto).toList();
    }

    @Override
    public List<PersonaDto> findClientesDtoList(Long cuentaId) {
        Cuenta c = findCuentaById(cuentaId);

        //if there is no acc for any reason
        if(c == null) throw new RuntimeException("El proveedor no tiene cuenta. findClientesDtoList en CuentaServiceImpl");

        List<Cliente> clientes = c.getClientes();
        if(clientes == null) return new ArrayList<>();
        return clientes.stream().map(PersonaMapper::mapPersonaToPersonaDto).toList();
    }

    @Override
    public List<ProductoDto> findProductosDtoList(Long cuentaId) {
        Cuenta c = findCuentaById(cuentaId);

        //if there is no acc for any reason
        if(c == null) throw new RuntimeException("El proveedor no tiene cuenta. findProductosDtoList en CuentaServiceImpl");

        List<Producto> productos = c.getProductos();
        if(productos == null) return new ArrayList<>();
        return productos.stream().map(ProductoMapper::mapProductoToProductoDto).toList();
    }
}
