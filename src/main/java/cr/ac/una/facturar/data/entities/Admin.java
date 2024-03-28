package cr.ac.una.facturar.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "admin")
@AllArgsConstructor
public class Admin extends Persona{
    @Column(name = "admin_pass", nullable = false, length = 64)
    private String pass;
}