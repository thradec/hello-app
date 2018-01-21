package cz.thradec.hello.security;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
public class LdapConfig {

    @Autowired
    private Environment env;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ObjectPostProcessor<Object> objectPostProcessor;

    @Bean
    @DependsOn("directoryServer")
    public LdapContextSource ldapContextSource() {
        String port = env.getProperty("local.ldap.port");
        String baseDn = env.getProperty("spring.ldap.embedded.base-dn");
        return new DefaultSpringSecurityContextSource(asList("ldap://localhost:" + port + "/"), baseDn);
    }

    @Bean
    public AuthenticationManager ldapAuthenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(objectPostProcessor)
                .ldapAuthentication()
                    .userDnPatterns("uid={0},ou=people")
                    .groupSearchBase("ou=groups")
                    .contextSource(ldapContextSource())
                    .passwordCompare()
                    .passwordEncoder(new PlaintextPasswordEncoder())
                    .passwordAttribute("userPassword")
                    .and()
                    .and()
                    .build();
    }

}