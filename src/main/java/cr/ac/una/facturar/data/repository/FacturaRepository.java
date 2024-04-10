package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}