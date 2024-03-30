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
    @Column(name = "proveedor_pass", length = 64)
    private String pass;

    @Column(name = "proveedor_acceso")
    private Boolean autorizado;

    @OneToOne
    @JoinColumn(name = "info_com_id")
    private Cuenta cuenta;
}