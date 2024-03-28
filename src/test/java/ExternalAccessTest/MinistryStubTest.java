package ExternalAccessTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(classes = MinistryStubTest.class)
public class MinistryStubTest {
    @Test
    void getTrueFromUserControllerWhenGetRequestSent() throws Exception {
        String uri = "http://localhost:8080/ministryStub/api/user/207930197";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
