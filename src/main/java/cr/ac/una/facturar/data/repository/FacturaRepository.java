package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findAllByCuenta(Cuenta c);
}