package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Cliente;
import cr.ac.una.facturar.data.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findById(String id);
}
