package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.ClienteDto;
import cr.ac.una.facturar.data.entities.Cliente;
import cr.ac.una.facturar.data.entities.Producto;

public interface ClienteService {
    ClienteDto findById(String id);

    boolean save(ClienteDto clienteDto);

    Cliente findClienteById(String id);

    Cliente save(Cliente c);
}
