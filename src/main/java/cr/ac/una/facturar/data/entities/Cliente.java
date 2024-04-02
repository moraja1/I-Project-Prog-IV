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
@DiscriminatorValue("Cliente")
public class Cliente extends Persona{
    @ManyToOne
    @JoinColumn(name = "cuenta_id",
            foreignKey = @ForeignKey(name = "CUENTA_CLIENTE_FK")
    )
    private Cuenta cuenta;
}
