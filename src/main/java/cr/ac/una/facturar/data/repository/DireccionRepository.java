package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}