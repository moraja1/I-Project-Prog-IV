package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.mappers.ClienteMapper;
import cr.ac.una.facturar.business.mappers.ProductoMapper;
import cr.ac.una.facturar.business.service.ClienteService;
import cr.ac.una.facturar.business.service.ProductoService;
import cr.ac.una.facturar.data.dto.ClienteDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProductoDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Cliente;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Producto;
import cr.ac.una.facturar.data.repository.ClienteRepository;
import cr.ac.una.facturar.data.repository.CuentaRepository;
import cr.ac.una.facturar.data.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository PR;
    private final CuentaRepository CR;
    @Autowired
    public ClienteServiceImpl(ClienteRepository PR, CuentaRepository CR) {
        this.PR = PR;
        this.CR = CR;
    }

    @Override
    public ClienteDto findById(String id) {
        Optional<Cliente> persistedProv = PR.findById(id);
        return persistedProv.map(ClienteMapper::mapClienteToClienteDto).orElse(null);
    }

    @Override
    public boolean save(ClienteDto cliente) {
        //Save
        Optional<Cliente> persisted = PR.findById(cliente.getId());
        if(persisted.isEmpty()) {
            Cliente toSave = ClienteMapper.mapClienteDtoToCliente(cliente);
            PR.save(toSave);
            return true;
        }

        //Si ya existe
        Cliente persistedEntity = persisted.get();
        updateEntity(persistedEntity, cliente);
        PR.save(persistedEntity);
        return true;
    }

    @Override
    public Cliente findClienteById(String id) {
        Optional<Cliente> cliente = PR.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public Cliente save(Cliente c) {
        return c != null ? PR.save(c) : null;
    }

    @Override
    public List<Cliente> findAllByCuenta(Cuenta c) {
        return PR.findAllByCuenta(c);
    }

    @Override
    public ClienteDto updateClientInfo(String id, PersonaDto user) {
        Optional<Cliente> client = PR.findById(id);
        if(client.isEmpty())return null;

        Cliente cliente = client.get();
        cliente.setName(user.name());
        cliente.setLastName(user.lastName());
        cliente.setPhoneNumber(user.phoneNumber());
        cliente.setEmail(user.email());
        cliente.setDireccion(user.direccion());

        return ClienteMapper.mapClienteToClienteDto(cliente);
    }

    private void updateEntity(Cliente entity, ClienteDto data) {
        entity.setId(data.getId());
        entity.setName(data.getName());
        entity.setLastName(data.getLastName());
        entity.setEmail(data.getEmail());
        entity.setPhoneNumber(data.getPhoneNumber());
        entity.setDtype(data.getDtype());
        //entity.setInfoComercial(data.getInfoComercialId());
    }
}
