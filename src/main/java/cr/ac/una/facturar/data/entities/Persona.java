package cr.ac.una.facturar.data.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance
@Table(name = "Persona")
public class Persona {
    @Id
    @Column(name = "id")
    protected String id;

    @Column(name = "nombre")
    protected String name;

    @Column(name = "apellidos")
    protected String lastName;

    @Column(name = "telefono")
    protected String phoneNumber;

    @Column(name = "correo")
    protected String email;

    @Column(name = "password", length = 64)
    private String pass;

    @Column(insertable = false, updatable = false)
    private String dtype;

    @OneToOne
    @JoinColumn(name = "info_com_id")
    private InformacionComercial infoComercial;
}
