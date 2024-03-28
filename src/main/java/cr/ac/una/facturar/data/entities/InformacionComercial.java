package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacionComercial {
    private String id;
    private Proveedor proveedor;
    private String razonSocial;
    private String nombre;
    private Direccion direccion;
}
