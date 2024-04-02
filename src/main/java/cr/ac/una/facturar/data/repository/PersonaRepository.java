package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, String> {
    Optional<Persona> findByEmailAndPass(String email, String pass);
}