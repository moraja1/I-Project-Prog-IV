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
    private String id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
}
