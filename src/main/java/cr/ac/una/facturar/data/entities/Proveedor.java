package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("Proveedor")
public class Proveedor extends Persona{
    @Column(name = "proveedor_acceso")
    private Boolean autorizado;

    @OneToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}