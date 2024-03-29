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

    @OneToOne(
            mappedBy = "proveedor",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private Cuenta cuenta;

    @Transient
    private InformacionComercial infoComercial;

    public void agragarCuenta(Cuenta cuenta) {
        cuenta.setProveedor(this);
        this.cuenta = cuenta;
    }

    public void eliminarCuenta() {
        if (cuenta != null) {
            cuenta.setProveedor(null);
            this.cuenta = null;
        }
    }
}