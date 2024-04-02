package cr.ac.una.facturar.business.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminServiceImplement adminService;

    @Test
    void AdminService_getListOfAdminDto_AssertDataExists(){
        //Act
        List<AdminDto> adminInfoList = adminService.obtenerTodos();

        //Assert
        assertThat(adminInfoList).isNotEmpty();
        assertThat(adminInfoList.size()).isGreaterThan(0);
        assertThat(adminInfoList.size()).isEqualTo(2);
    }

    @Test
    void AdminService_ReturnAdminByEmailAndPassForLogging(){
        //Act
        Optional<AdminDto> adminDto = adminService.obtenerPorEmailYPass("admin2@admin.com", "testingLikeNeva");

        //Assert
        assertThat(adminDto.isPresent()).isEqualTo(true);
        assertThat(adminDto.get().getId()).isEqualTo("402290985");
    }

}
