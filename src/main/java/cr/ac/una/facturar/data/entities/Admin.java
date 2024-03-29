package cr.ac.una.facturar.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
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
@AllArgsConstructor
@DiscriminatorValue("Admin")
public class Admin extends Persona{
    @Column(name = "admin_pass", length = 64)
    private String pass;
}