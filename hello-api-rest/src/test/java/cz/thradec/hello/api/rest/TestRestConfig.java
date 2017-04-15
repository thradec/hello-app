package cz.thradec.hello.api.rest;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@Import(RestConfig.class)
public class TestRestConfig {

    @Bean
    public MockMvc mockMvc(WebApplicationContext wac) {
        return MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

}