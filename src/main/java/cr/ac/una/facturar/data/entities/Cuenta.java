package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cliente> clientes;

    @OneToOne
    @JoinColumn(name = "info_com_id")
    private InformacionComercial infoComercial;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Producto> productos;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Factura> facturas;

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        cliente.setCuenta(this);
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
        cliente.setCuenta(null);
    }

    public void agregarFactura(Factura factura) {
        facturas.add(factura);
        factura.setCuenta(this);
    }

    public void eliminarFactura(Factura factura) {
        facturas.remove(factura);
        factura.setCuenta(null);
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setCuenta(this);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        producto.setCuenta(null);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Cuenta cuenta = (Cuenta) o;
        return getId() != null && Objects.equals(getId(), cuenta.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", clientes=" + clientes +
                ", infoComercial=" + infoComercial +
                ", productos=" + productos +
                ", facturas=" + facturas +
                '}';
    }
}
