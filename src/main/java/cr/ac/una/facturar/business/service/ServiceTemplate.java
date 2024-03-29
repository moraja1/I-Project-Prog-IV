package cr.ac.una.facturar.business.service;

import java.util.List;
import java.util.Optional;

public interface ServiceTemplate<T> {
    Optional<T> obtenerPorEmailYPass(String email, String pass);
    List<T> obtenerTodos();
}
