package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FacturaProductoCantidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "producto_id")
    private Producto productoId;

    @ManyToOne
    @JoinColumn(name = "factura_id",
            foreignKey = @ForeignKey(name = "FACTURA_PRODUCTO_FK")
    )
    private Factura factura;

    private Long cantidad;
    private Long costo;
}
