package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Producto {
    @Id
    private Long id;
    private Long costo;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id",
            foreignKey = @ForeignKey(name = "CUENTA_PRODUCTO_FK")
    )
    private Cuenta cuenta;
}
