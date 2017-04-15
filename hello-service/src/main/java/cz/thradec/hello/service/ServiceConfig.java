package cz.thradec.hello.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:/service.properties")
public class ServiceConfig {
}