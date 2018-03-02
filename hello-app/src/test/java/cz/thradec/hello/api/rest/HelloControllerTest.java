package cz.thradec.hello.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.thradec.hello.AbstractTest;
import cz.thradec.hello.domain.Hello;
import cz.thradec.hello.domain.HelloRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerTest extends AbstractTest {

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

        then(helloRepository).should().save(any(Hello.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldDeleteHello() throws Exception {
        mvc.perform(delete("/api/hello/{id}", 1))
                .andExpect(status().isOk());

        then(helloRepository).should().deleteById(1L);
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