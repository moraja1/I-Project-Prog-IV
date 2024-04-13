package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.ClienteDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.entities.Cliente;

public class ClienteMapper {
    public static PersonaDto mapClienteDtoToPersonaDto(ClienteDto cliente) {
        return PersonaDto.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .lastName(cliente.getLastName())
                .phoneNumber(cliente.getPhoneNumber())
                .email(cliente.getEmail())
                .direccion(cliente.getDireccion())
                .build();
    }
    public static ClienteDto mapClienteToClienteDto(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .lastName(cliente.getLastName())
                .phoneNumber(cliente.getPhoneNumber())
                .email(cliente.getEmail())
                .dtype(cliente.getDtype())
                .direccion(cliente.getDireccion())
                .build();
    }

    public static Cliente mapClienteDtoToCliente(ClienteDto client) {
        return Cliente.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .direccion(client.getDireccion())
                .dtype(client.getDtype())
                .build();
    }
}
//.infoComercial(client.getInfoComercialId(long id))