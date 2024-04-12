package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.entities.Producto;
import cr.ac.una.facturar.data.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findById(Long id);

    List<Producto> findAllByCuenta(Cuenta c);
}
