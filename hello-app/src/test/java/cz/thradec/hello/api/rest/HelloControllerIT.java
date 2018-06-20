package cz.thradec.hello.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.thradec.hello.AbstractIT;
import cz.thradec.hello.domain.Hello;
import cz.thradec.hello.domain.HelloRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HelloControllerIT extends AbstractIT {

    private final MockMvc mvc;
    private final ObjectMapper mapper;
    @MockBean
    private HelloRepository helloRepository;

    HelloControllerIT(@Autowired MockMvc mvc, @Autowired ObjectMapper mapper) {
        this.mvc = mvc;
        this.mapper = mapper;
    }

    @Test
    void shouldGetHello() throws Exception {
        given(helloRepository.findById(1L))
                .willReturn(new Hello("mock"));

        mvc.perform(get("/api/hello/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("mock"));
    }

    @Test
    @WithMockUser
    void shouldValidateHello() throws Exception {
        mvc.perform(post("/api/hello")
                .contentType(APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException()).isInstanceOf(MethodArgumentNotValidException.class));
    }

    @Test
    @WithMockUser
    void shouldSaveHello() throws Exception {
        mvc.perform(post("/api/hello")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(new Hello("test"))))
                .andExpect(status().isOk());

        then(helloRepository).should().save(any(Hello.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteHello() throws Exception {
        mvc.perform(delete("/api/hello/{id}", 1))
                .andExpect(status().isOk());

        then(helloRepository).should().deleteById(1L);
    }

    @Test
    void shouldReturn401ForUnauthorized() throws Exception {
        mvc.perform(post("/api/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "INSUFFICIENT")
    void shouldReturn403ForForbidden() throws Exception {
        mvc.perform(delete("/api/hello/{id}", 1))
                .andExpect(status().isForbidden());
    }

}