package cr.ac.una.facturar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IProjectProgIvApplicationTest {
    @Test
    void contextLoads(){
        System.out.println(String.valueOf(TiposCedula.Fisica));
    }
}