package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.data.dto.AdminDto;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    Optional<T> obtenerPorEmailYPass(String email, String pass);
    List<T> obtenerTodos();
}
