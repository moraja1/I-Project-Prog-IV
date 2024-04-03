package cr.ac.una.facturar.presentacion.model;

import lombok.Builder;

@Builder
public record RegisteredUser(String id, String name, String lastName) {}
