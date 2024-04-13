package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private Date date;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clientInfo;
    private Long costoTotal;

    @ManyToOne
    @JoinColumn(name = "cuenta_id",
            foreignKey = @ForeignKey(name = "CUENTA_FACTURA_FK")
    )
    private Cuenta cuenta;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacturaProductoCantidad> productoCantidad;

    public void agregarProductoCantidad(FacturaProductoCantidad producto) {
        productoCantidad.add(producto);
        producto.setFactura(this);
    }

    public void eliminarProductoCantidad(FacturaProductoCantidad producto) {
        productoCantidad.remove(producto);
        producto.setFactura(null);
    }
}
