package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clientInfo;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    private Double iva;
    private Long costoTotal;

    @ManyToOne
    @JoinColumn(name = "cuenta_id",
            foreignKey = @ForeignKey(name = "CUENTA_FACTURA_FK")
    )
    private Cuenta cuenta;

    @OneToOne
    @JoinColumn(name = "factura_producto_cant_id")
    private FacturaProductoCantidad productoCantidad;

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setFactura(this);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        producto.setFactura(null);
    }
}
