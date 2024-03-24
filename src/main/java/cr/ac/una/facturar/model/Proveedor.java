package cr.ac.una.facturar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proveedor extends Persona{
    private String tipoId;
    private String username;
    private String contrasena;
    private InformacionComercial infoComercial;
    private Boolean estaAutorizado;
}