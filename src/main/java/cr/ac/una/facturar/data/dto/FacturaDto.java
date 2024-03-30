package cr.ac.una.facturar.data.dto;

import cr.ac.una.facturar.data.dto.ClienteDto;
import cr.ac.una.facturar.data.dto.InformacionComercialDto;
import cr.ac.una.facturar.data.dto.ProductoDto;
import cr.ac.una.facturar.data.entities.Factura;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Factura}
 */
@Data
@Builder
public class FacturaDto implements Serializable {
    Long id;
    LocalDateTime date;
    InformacionComercialDto infoComercial;
    ClienteDto clientInfo;
    Set<ProductoDto> productos;
    Double iva;
    Long costoTotal;
}