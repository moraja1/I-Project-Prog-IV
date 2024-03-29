package cr.ac.una.facturar.business.service;

import cr.ac.una.facturar.business.service.impl.ProveedorServiceImplement;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProveedorServiceImplementTest {
    @Autowired
    private ProveedorServiceImplement proveedorService;

    @Test
    void ProveedorService_ReturnsProveedorDtoByEmailAndPassForLogging(){
        //Act
        Optional<ProveedorDto> dto = proveedorService.obtenerPorEmailYPass("steven@proveedor.com", "pass2");

        //Assert
        assertThat(dto.isPresent()).isTrue();
        assertThat(dto.get().getId()).isEqualTo("802040608");
    }

    @Test
    void ProveedorService_ReturnsAllProveedorInDatabase(){
        //Act
        List<ProveedorDto> proveedorDtoList = proveedorService.obtenerTodos();

        //Assert
        assertThat(proveedorDtoList).isNotEmpty();
        assertThat(proveedorDtoList.size()).isEqualTo(6);
    }

    @Test
    void ProveedorService_ReturnsAllProveedorWithAccessInDatabase(){
        //act
        List<ProveedorDto> dtos = proveedorService.obtenerProveedoresAutorizados();

        //assert
        assertThat(dtos).isNotEmpty();
        assertThat(dtos.size()).isEqualTo(2);
    }
}