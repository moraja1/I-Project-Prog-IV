package cr.ac.una.facturar.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProveedorRepositoryTest {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Test
    void ProveedorRepository_findAllEntities_CorrectFindingOfAll(){

    }
}