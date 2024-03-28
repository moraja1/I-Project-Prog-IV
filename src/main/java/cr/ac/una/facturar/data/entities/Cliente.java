package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
