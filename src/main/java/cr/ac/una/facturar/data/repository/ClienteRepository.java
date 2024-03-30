package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}