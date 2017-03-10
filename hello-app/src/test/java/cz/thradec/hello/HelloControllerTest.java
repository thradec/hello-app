package cz.thradec.hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

        ResponseEntity<Hello> response = restTemplate.getForEntity("/api/hello/{id}", Hello.class, 1L);

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().getMessage()).isEqualTo("mock");
    }

    @Test
    public void shouldValidateHello() {
        ResponseEntity<Map> response = restTemplate.postForEntity("/api/hello", new Hello(), Map.class);

        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST);
        assertThat(response.getBody()).contains(entry("exception", MethodArgumentNotValidException.class.getName()));
    }

    @Test
    public void shouldReturn500ForUnexpectedException() {
        given(helloRepository.findById(1L)).willThrow(NullPointerException.class);

        ResponseEntity<Map> response = restTemplate.getForEntity("/api/hello/{id}", Map.class, 1L);

        assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).contains(entry("exception", NullPointerException.class.getName()));
    }

}