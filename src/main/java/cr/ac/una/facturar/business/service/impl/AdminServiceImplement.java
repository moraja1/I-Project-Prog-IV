package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.business.service.Service;
import cr.ac.una.facturar.data.dto.AdminDto;
import cr.ac.una.facturar.data.entities.Admin;
import cr.ac.una.facturar.data.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class AdminServiceImplement implements Service<AdminDto> {
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
        AdminDto adminInfo = mapAdminToAdminDto(admin);

        return Optional.of(adminInfo);
    }

    @Override
    public List<AdminDto> obtenerTodos() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(this::mapAdminToAdminDto).toList();
    }

    private AdminDto mapAdminToAdminDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .name(admin.getName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }
}
