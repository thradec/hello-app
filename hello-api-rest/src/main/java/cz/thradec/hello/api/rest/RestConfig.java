package cz.thradec.hello.api.rest;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

import cz.thradec.hello.service.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@EnableWebSecurity
@ComponentScan
@Import(ServiceConfig.class)
public class RestConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .formLogin()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new BasicAuthenticationEntryPoint())
                .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers(POST, "/api/**").authenticated()
                    .antMatchers(DELETE, "/api/**").hasRole("ADMIN");
    }

}