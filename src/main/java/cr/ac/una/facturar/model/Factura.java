package cr.ac.una.facturar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factura {
    private String id;
    private LocalDateTime date;
    private InformacionComercial infoComercial;
    private Cliente clientInfo;
    private List<Producto> productos;
    private Double iva;
    private Long costoTotal;
}
