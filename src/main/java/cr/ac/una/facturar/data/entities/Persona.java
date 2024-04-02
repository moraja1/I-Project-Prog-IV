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
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "tipo_persona")
public abstract class Persona {
    @Id
    @Column(name = "id")
    protected String id;

    @Column(name = "tipo_id")
    @Enumerated(EnumType.STRING)
    private TiposCedula tipoId;

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

    @OneToOne
    @JoinColumn(name = "info_com_id")
    private InformacionComercial infoComercial;
}
