package cr.ac.una.facturar.data.entities;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Proveedor extends Persona{
    private String tipoId;
    private String username;
    private String contrasena;
    private InformacionComercial infoComercial;
    private Boolean estaAutorizado;
}