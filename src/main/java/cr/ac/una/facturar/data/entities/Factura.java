package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

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
    @JoinColumn(name = "info_com_id")
    private InformacionComercial infoComercial;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clientInfo;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Producto> productos;

    private Double iva;
    private Long costoTotal;

    @ManyToOne
    @JoinColumn(name = "cuenta_id",
            foreignKey = @ForeignKey(name = "CUENTA_FACTURA_FK")
    )
    private Cuenta cuenta;

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setFactura(this);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        producto.setFactura(null);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Factura factura = (Factura) o;
        return getId() != null && Objects.equals(getId(), factura.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", date=" + date +
                ", infoComercial=" + infoComercial +
                ", clientInfo=" + clientInfo +
                ", productos=" + productos +
                ", iva=" + iva +
                ", costoTotal=" + costoTotal +
                '}';
    }
}
