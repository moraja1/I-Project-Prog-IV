package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.ServiceTemplate;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Proveedor;
import cr.ac.una.facturar.data.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImplement implements ServiceTemplate<ProveedorDto> {
    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplement(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public Optional<ProveedorDto> obtenerPorEmailYPass(String email, String pass) {
        Optional<Proveedor> proveedor = proveedorRepository.findByEmailAndPass(email, pass);
        if (proveedor.isEmpty()) return Optional.empty();

        return Optional.of(mapProveedorToDto(proveedor.get()));

    }

    @Override
    public List<ProveedorDto> obtenerTodos() {
        List<Proveedor> proveedors = proveedorRepository.findAll();
        return proveedors.stream().map(this::mapProveedorToDto).collect(Collectors.toList());
    }

    public List<ProveedorDto> obtenerProveedoresAutorizados() {
        List<Proveedor> proveedors = proveedorRepository.findByAutorizado(true);
        return proveedors.stream().map(this::mapProveedorToDto).collect(Collectors.toList());
    }

    public List<ProveedorDto> obtenerProveedoresNoAutorizados() {
        List<Proveedor> proveedors = proveedorRepository.findByAutorizado(false);
        return proveedors.stream().map(this::mapProveedorToDto).collect(Collectors.toList());
    }

    private ProveedorDto mapProveedorToDto(Proveedor proveedor) {
        return ProveedorDto.builder()
                .id(proveedor.getId())
                .tipoId(proveedor.getTipoId())
                .name(proveedor.getName())
                .lastName(proveedor.getLastName())
                .email(proveedor.getEmail())
                .phoneNumber(proveedor.getPhoneNumber())
                .estaAutorizado(proveedor.getAutorizado())
                .build();
    }


}
