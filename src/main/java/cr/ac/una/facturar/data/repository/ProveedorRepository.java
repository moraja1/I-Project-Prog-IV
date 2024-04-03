package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, String> {
    Optional<Proveedor> findById(String id);
    List<Proveedor> findAllByAutorizado(Boolean value);
}