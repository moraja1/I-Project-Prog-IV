package cr.ac.una.facturar.data.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link cr.ac.una.facturar.data.entities.Admin}
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdminDto implements Serializable {
    private String id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
}