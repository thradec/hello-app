package cz.thradec.hello.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("user").password("user").roles("USER");
    }

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
