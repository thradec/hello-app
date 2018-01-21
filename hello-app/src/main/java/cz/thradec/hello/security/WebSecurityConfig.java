package cz.thradec.hello.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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