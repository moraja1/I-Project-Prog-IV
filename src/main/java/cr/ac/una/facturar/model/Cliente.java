package cr.ac.una.facturar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends Persona{
    private String tipoId;
    private InformacionComercial infoComercial;

}
