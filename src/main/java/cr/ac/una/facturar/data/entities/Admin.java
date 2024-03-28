package cr.ac.una.facturar.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin extends Persona{
    @Column(name = "admin_pass", nullable = false, length = 64)
    private String admin_pass;

}
