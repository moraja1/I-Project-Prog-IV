package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Clientes")
public class Cliente extends Persona{

    @ManyToOne
    @JoinColumn(name = "cuenta_id",
            foreignKey = @ForeignKey(name = "CUENTA_ID_FK")
    )
    private Cuenta cuenta;

    @Transient
    private InformacionComercial infoComercial;
}
