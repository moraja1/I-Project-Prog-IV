package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Proveedor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProveedorRepositoryTest {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    void ProveedorRepository_findAllEntities_CorrectFindingAll(){
        //Act
        List<Proveedor> proveedores = proveedorRepository.findAll();

        //Assert
        assertThat(proveedores).isNotEmpty();
        assertThat(proveedores.size()).isEqualTo(6);
    }
}