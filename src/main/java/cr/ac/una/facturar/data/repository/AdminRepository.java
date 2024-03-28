package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByEmailAndPass(String email, String password);
}