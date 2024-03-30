package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}