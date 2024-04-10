package cr.ac.una.facturar.business.service.impl;

import cr.ac.una.facturar.data.dto.CuentaDto;
import cr.ac.una.facturar.data.dto.PersonaDto;
import cr.ac.una.facturar.data.dto.ProveedorDto;
import cr.ac.una.facturar.data.entities.Cuenta;
import cr.ac.una.facturar.data.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CuentaServiceImplTest {

    @Autowired
    CuentaServiceImpl cuentaService;
    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    void CuentaIsBeingCreated() {
        PersonaDto p = PersonaDto.builder()
                .id("207040108")
                .build();

        ProveedorDto prov = cuentaService.addCuentaToProv(p);
        CuentaDto c = cuentaService.findById(prov.getCuentaId());
        Optional<Cuenta> persisted = cuentaRepository.findById(c.getId());

        assertThat(prov).isNotNull();
        assertThat(c).isNotNull();
        assertThat(persisted.isPresent()).isTrue();
        assertThat(persisted.get().getId()).isEqualTo(c.getId());
    }
}