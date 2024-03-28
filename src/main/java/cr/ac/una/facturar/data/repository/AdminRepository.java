package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByEmailAndPass(String email, String password);
    Boolean existsAdminByEmailAndPass(String email, String password);
}