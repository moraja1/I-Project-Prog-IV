package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.InformacionComercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionComercialRepository extends JpaRepository<InformacionComercial, Long> {
}