package cz.thradec.hello.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(ldapContextSource())
                .passwordCompare()
                    .passwordEncoder(NoOpPasswordEncoder.getInstance())
                    .passwordAttribute("userPassword");
    }

    @Bean
    @DependsOn("directoryServer")
    public LdapContextSource ldapContextSource() {
        String port = env.getProperty("local.ldap.port");
        String baseDn = env.getProperty("spring.ldap.embedded.base-dn");
        return new DefaultSpringSecurityContextSource(asList("ldap://localhost:" + port + "/"), baseDn);
    }

}