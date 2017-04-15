package cz.thradec.hello.api.rest;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyObject;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.thradec.hello.service.Hello;
import cz.thradec.hello.service.HelloRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRestConfig.class, webEnvironment = RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private HelloRepository helloRepository;

    @Test
    public void shouldGetHello() throws Exception {
        given(helloRepository.findById(1L))
                .willReturn(new Hello("mock"));

        mvc.perform(get("/api/hello/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("mock"));
    }

    @Test
    @WithMockUser
    public void shouldValidateHello() throws Exception {
        mvc.perform(post("/api/hello")
                .contentType(APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> result.getResolvedException().equals(MethodArgumentNotValidException.class));
    }

    @Test
    @WithMockUser
    public void shouldSaveHello() throws Exception {
        mvc.perform(post("/api/hello")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(new Hello("test"))))
                .andExpect(status().isOk());

        then(helloRepository).should().save(anyObject());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldDeleteHello() throws Exception {
        mvc.perform(delete("/api/hello/{id}", 1))
                .andExpect(status().isOk());

        then(helloRepository).should().delete(1L);
    }

    @Test
    public void shouldReturn401ForUnauthorized() throws Exception {
        mvc.perform(post("/api/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "INSUFFICIENT")
    public void shouldReturn403ForForbidden() throws Exception {
        mvc.perform(delete("/api/hello/{id}", 1))
                .andExpect(status().isForbidden());
    }

}