package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.ServiceTemplate;
import cr.ac.una.facturar.data.dto.AdminDto;
import cr.ac.una.facturar.data.entities.Admin;
import cr.ac.una.facturar.data.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplement implements ServiceTemplate<AdminDto> {
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImplement(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Optional<AdminDto> obtenerPorEmailYPass(String email, String pass){
        Optional<Admin> savedAdmin = adminRepository.findByEmailAndPass(email, pass);
        if (savedAdmin.isEmpty()) {
            return Optional.empty();
        }

        Admin admin = savedAdmin.get();
        AdminDto adminInfo = mapAdminToDto(admin);

        return Optional.of(adminInfo);
    }

    @Override
    public List<AdminDto> obtenerTodos() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(this::mapAdminToDto).toList();
    }

    private AdminDto mapAdminToDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .name(admin.getName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }
}
