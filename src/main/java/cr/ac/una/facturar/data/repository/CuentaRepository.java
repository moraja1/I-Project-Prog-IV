package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}