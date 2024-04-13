package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.*;
import cr.ac.una.facturar.business.service.*;
import cr.ac.una.facturar.data.dto.*;
import cr.ac.una.facturar.data.entities.*;
import cr.ac.una.facturar.data.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cr.ac.una.facturar.business.mappers.ClienteMapper.mapClienteDtoToCliente;
import static cr.ac.una.facturar.business.mappers.CuentaMapper.mapCuentaToDto;
import static cr.ac.una.facturar.business.mappers.FacturaProductoCantidadMapper.mapDtoToFacturaProductoCantidad;
import static cr.ac.una.facturar.business.mappers.ProductoMapper.mapProductoDtoToProducto;

@Service
public class CuentaServiceImpl implements CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ProveedorService proveedorService;
    private final ClienteService clienteService;
    private final FacturaService facturaService;
    private final ProductoService productoService;
    private final FacturaProductoCantidadRepository facturaProductoCantidadRepository;

    @Autowired
    public CuentaServiceImpl(CuentaRepository cuentaRepository, ProveedorService proveedorService, ClienteService clienteService, FacturaService facturaService, ProductoService productoService, FacturaProductoCantidadRepository facturaProductoCantidadRepository) {
        this.cuentaRepository = cuentaRepository;
        this.proveedorService = proveedorService;
        this.clienteService = clienteService;
        this.facturaService = facturaService;
        this.productoService = productoService;
        this.facturaProductoCantidadRepository = facturaProductoCantidadRepository;
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

        List<Factura> facturas = facturaService.findAllByCuenta(c);
        if(facturas == null) return new ArrayList<>();
        return facturas.stream().map(FacturaMapper::mapFacturaToFacturaDto).toList();
    }

    @Override
    public List<PersonaDto> findClientesDtoList(Long cuentaId) {
        Cuenta c = findCuentaById(cuentaId);

        //if there is no acc for any reason
        if(c == null) throw new RuntimeException("El proveedor no tiene cuenta. findClientesDtoList en CuentaServiceImpl");

        List<Cliente> clientes = clienteService.findAllByCuenta(c);
        if(clientes == null) return new ArrayList<>();
        return clientes.stream().map(PersonaMapper::mapPersonaToPersonaDto).toList();
    }

    @Override
    public List<ProductoDto> findProductosDtoList(Long cuentaId) {
        Cuenta c = findCuentaById(cuentaId);

        //if there is no acc for any reason
        if(c == null) throw new RuntimeException("El proveedor no tiene cuenta. findProductosDtoList en CuentaServiceImpl");

        List<Producto> productos = productoService.findAllByCuenta(c);
        if(productos == null) return new ArrayList<>();
        return productos.stream().map(ProductoMapper::mapProductoToProductoDto).toList();
    }

    @Override
    public boolean addClient(Long cuentaId, ClienteDto cliente) {
        Cuenta c = findCuentaById(cuentaId);

        //if there is no acc for any reason
        if(c == null) throw new RuntimeException("El proveedor no tiene cuenta. addClient en CuentaServiceImpl");

        //Save Client
        Cliente client = mapClienteDtoToCliente(cliente);
        client = clienteService.save(client);

        //Evaluates operation
        if(client == null) return false;

        //Associate Cliente to Cuenta
        c.agregarCliente(client);
        cuentaRepository.save(c);
        return true;
    }

    @Override
    public boolean addProducto(Long cuentaId, ProductoDto producto) {
        Cuenta c = findCuentaById(cuentaId);

        //if there is no acc for any reason
        if(c == null) throw new RuntimeException("El proveedor no tiene cuenta. addProducto en CuentaServiceImpl");

        //Save Client
        Producto product = mapProductoDtoToProducto(producto);
        product = productoService.save(product);

        //Evaluates operation
        if(product == null) return false;

        //Associate Cliente to Cuenta
        c.agregarProducto(product);
        cuentaRepository.save(c);
        return true;
    }

    @Override
    public boolean declareInvoice(FacturaDto facturaDto) {
        Optional<Cuenta> cuentaOp = cuentaRepository.findById(facturaDto.getCuentaId());
        if(cuentaOp.isEmpty()) return false;
        Cuenta cuenta = cuentaOp.get();

        List<FacturaProductoCantidadDto> facturaProductoCantidad = facturaDto.getFacturaProductoCantidad();
        if(facturaProductoCantidad.isEmpty()) return false;

        List<FacturaProductoCantidad> productoCantidadList = new ArrayList<>();
        for(var facturaProdCant : facturaProductoCantidad) {
            Producto producto = productoService.findProductoById(facturaProdCant.getProductoId());
            productoCantidadList.add(mapDtoToFacturaProductoCantidad(facturaProdCant, producto));
        }

        Cliente cliente = clienteService.findClienteById(facturaDto.getClientId());
        Factura factura = FacturaMapper.mapFacturaDtoToFactura(facturaDto, cliente);
        for(var proCant : productoCantidadList) {
            facturaProductoCantidadRepository.save(proCant);
            factura.agregarProductoCantidad(proCant);
        }

        factura = facturaService.save(factura);
        cuenta.agregarFactura(factura);

        cuentaRepository.save(cuenta);
        return true;
    }
}
