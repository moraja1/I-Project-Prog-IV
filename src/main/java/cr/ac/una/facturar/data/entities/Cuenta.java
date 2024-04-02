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
}
