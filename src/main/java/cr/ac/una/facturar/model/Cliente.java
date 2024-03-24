package cr.ac.una.facturar.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends Persona{
    private String tipoId;
    private InformacionComercial infoComercial;

}
