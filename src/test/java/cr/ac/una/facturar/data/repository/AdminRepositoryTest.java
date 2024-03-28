package cr.ac.una.facturar.data.repository;

import cr.ac.una.facturar.data.entities.Admin;
import cr.ac.una.facturar.data.entities.TiposCedula;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;

    @Test
    void AdminRepository_SaveAdmin_SavedCorrectly(){
        //Arrange
        Admin admin = Admin.builder()
                .id("201010202")
                .tipoId(TiposCedula.Fisica)
                .email("pruba@admin.com")
                .name("Pruebencio")
                .lastName("Testero")
                .phoneNumber("88888888")
                .build();
        admin.setPass("prueba");

        //Act
        Admin savedAdmin = adminRepository.save(admin);

        //Assert
        assertThat(savedAdmin).isNotNull();
        assertThat(admin).isEqualTo(savedAdmin);
    }

    @Test
    void AdminRepository_SavedAdmin_FindByEmailAndPasswordCorrectly(){
        //Act
        Optional<Admin> savedAdmin = adminRepository.findByEmailAndPass("admin@admin.com", "admin");

        //Assert
        assertThat(savedAdmin.isPresent()).isEqualTo(true);
        assertThat(adminRepository.existsAdminByEmailAndPass("admin@admin.com", "admin")).isEqualTo(true);
    }
}
