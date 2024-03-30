package cr.ac.una.facturar.data.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provincia;
    private String canton;
    private String distrito;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(id, direccion.id) && Objects.equals(provincia, direccion.provincia) &&
                Objects.equals(canton, direccion.canton) && Objects.equals(distrito, direccion.distrito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, provincia, canton, distrito);
    }
}
