package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.ClienteDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.entities.Cliente;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.InformacionComercial;

public class ClienteMapper {
    public static PersonaDto mapClienteDtoToPersonaDto(ClienteDto cliente) {
        return PersonaDto.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .lastName(cliente.getLastName())
                .phoneNumber(cliente.getPhoneNumber())
                .email(cliente.getEmail())
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
                .infoComercialId(cliente.getInfoComercial().getId())
                .build();
    }

    public static Cliente mapClienteDtoToCliente(ClienteDto client) {
        return Cliente.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .dtype(client.getDtype())
                .build();
    }
}
//.infoComercial(client.getInfoComercialId(long id))