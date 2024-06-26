package cr.ac.una.facturar.business.mappers;

import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ProveedorMapper {

    public static PersonaDto mapProveedorDtoToPersonaDto(ProveedorDto proveedor) {
        return PersonaDto.builder()
                .id(proveedor.getId())
                .name(proveedor.getName())
                .lastName(proveedor.getLastName())
                .phoneNumber(proveedor.getPhoneNumber())
                .email(proveedor.getEmail())
                .build();
    }

    public static Proveedor mapProveedorDtoToProveedor(ProveedorDto proveedor, Cuenta cuenta) {
        return Proveedor.builder()
                .id(proveedor.getId())
                .name(proveedor.getName())
                .lastName(proveedor.getLastName())
                .email(proveedor.getEmail())
                .phoneNumber(proveedor.getPhoneNumber())
                .pass(proveedor.getPass())
                .autorizado(proveedor.getAutorizado())
                .cuenta(cuenta)
                .build();
    }

    public static ProveedorDto mapProveedorToProveedorDto(Proveedor proveedor) {
        return ProveedorDto.builder()
                .id(proveedor.getId())
                .name(proveedor.getName())
                .lastName(proveedor.getLastName())
                .phoneNumber(proveedor.getPhoneNumber())
                .dtype(proveedor.getDtype())
                .email(proveedor.getEmail())
                .autorizado(proveedor.getAutorizado())
                .cuentaId((proveedor.getCuenta() != null) ? proveedor.getCuenta().getId() : null)
                .build();
    }
}
