package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}