package cz.thradec.hello.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    @DependsOn("directoryServer")
    public LdapContextSource ldapContextSource() {
        String port = env.getProperty("local.ldap.port");
        return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:" + port + "/"), "dc=thradec,dc=cz");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(ldapContextSource())
                .passwordCompare()
                .passwordEncoder(new PlaintextPasswordEncoder())
                .passwordAttribute("userPassword");
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
