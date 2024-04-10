package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.ProveedorMapper;
import cr.ac.una.facturar.business.service.InformacionComercialService;
import cr.ac.una.facturar.business.service.ProveedorService;
import cr.ac.una.facturar.data.dto.FacturaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.InformacionComercial;
import cr.ac.una.facturar.data.entities.Proveedor;
import cr.ac.una.facturar.data.repository.CuentaRepository;
import cr.ac.una.facturar.data.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    private final ProveedorRepository proveedorRepository;
    private final InformacionComercialService infoComercialService;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository, InformacionComercialService infoComercialService, CuentaRepository cuentaRepository) {
        this.proveedorRepository = proveedorRepository;
        this.infoComercialService = infoComercialService;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public ProveedorDto findById(String id) {
        Optional<Proveedor> persistedProv = proveedorRepository.findById(id);

        return persistedProv.map(ProveedorMapper::mapProveedorToProveedorDto).orElse(null);

    }

    @Override
    public List<ProveedorDto> findAllByAutorizado(boolean b) {
        List<Proveedor> proveedorList = proveedorRepository.findAllByAutorizado(b);
        return proveedorList.stream().map(ProveedorMapper::mapProveedorToProveedorDto).toList();
    }

    @Override
    public boolean save(ProveedorDto proveedor) {
        //Look if prov has account
        Long cuentaId = proveedor.getCuentaId();
        Optional<Cuenta> cOptional = cuentaRepository.findById(cuentaId);
        Cuenta cuenta = cOptional.orElse(null);

        //Look if prov has InfoComercial
        Long infoComercialId = proveedor.getInfoComercialId();
        InformacionComercial infoComercial = (infoComercialId != null) ?
                infoComercialService.findInfoComercialById(infoComercialId) : null;

        //Save
        Optional<Proveedor> persisted = proveedorRepository.findById(proveedor.getId());
        if(persisted.isEmpty()) {
            //Creates proveedor
            Proveedor toSave = ProveedorMapper.mapProveedorDtoToProveedor(proveedor, cuenta, infoComercial);
            proveedorRepository.save(toSave);
            return true;
        }

        //Si ya existe
        Proveedor persistedEntity = persisted.get();
        updateEntity(persistedEntity, proveedor, cuenta, infoComercial);

        proveedorRepository.save(persistedEntity);

        return true;
    }

    @Override
    public Proveedor findProveedorById(String id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        return proveedor.orElse(null);
    }

    @Override
    public Proveedor save(Proveedor p) {
        p = proveedorRepository.save(p);
        return p;
    }

    private void updateEntity(Proveedor entity, ProveedorDto data, Cuenta cuenta, InformacionComercial infoComercial) {
        entity.setName(data.getName());
        entity.setLastName(data.getLastName());
        entity.setAutorizado(data.getAutorizado());
        entity.setEmail(data.getEmail());
        entity.setPhoneNumber(data.getPhoneNumber());
        entity.setCuenta(cuenta);
        entity.setInfoComercial(infoComercial);
    }
}
