package cr.ac.una.facturar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {
    protected String id;
    protected String name;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
}
