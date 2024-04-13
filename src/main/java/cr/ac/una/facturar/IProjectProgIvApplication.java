package cr.ac.una.facturar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;

@SpringBootApplication
public class IProjectProgIvApplication {

    public static void main(String[] args) {
        SpringApplication.run(IProjectProgIvApplication.class, args);
    }

}
