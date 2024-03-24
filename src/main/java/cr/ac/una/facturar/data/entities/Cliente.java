package cr.ac.una.facturar.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cliente extends Persona{
    private String tipoId;

    @OneToOne
    @JoinColumn(name = "info_comercial_id")
    private InformacionComercial infoComercial;
}
