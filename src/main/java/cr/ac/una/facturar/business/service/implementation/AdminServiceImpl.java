package cr.ac.una.facturar.business.service.implementation;

import cr.ac.una.facturar.business.service.AdminService;
import cr.ac.una.facturar.data.dto.AdminDto;
import cr.ac.una.facturar.data.entities.Admin;
import cr.ac.una.facturar.data.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Boolean elAccesoEsValido(String email, String password) {
        Optional<Admin> admin = Optional.ofNullable(adminRepository.findByEmailAndPass(email, password));
        return admin.isPresent();
    }
}
