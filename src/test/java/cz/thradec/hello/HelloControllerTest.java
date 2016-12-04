package cz.thradec.hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

import cz.thradec.hello.domain.Hello;
import cz.thradec.hello.domain.HelloRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private HelloRepository helloRepository;

    @Test
    public void shouldGetHello() {
        given(helloRepository.findById(1L)).willReturn(new Hello(1L, "mock"));

        ResponseEntity<Hello> response = restTemplate.getForEntity("/hello/{id}", Hello.class, 1L);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getMessage()).isEqualTo("mock");
    }

}