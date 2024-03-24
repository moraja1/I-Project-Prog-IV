package cr.ac.una.facturar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Direccion {
    private String id;
    private InformacionComercial infoComercial;
    private String provincia;
    private String canton;
    private String distrito;
}
