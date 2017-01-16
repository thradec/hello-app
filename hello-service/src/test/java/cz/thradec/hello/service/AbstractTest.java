package cz.thradec.hello.service;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import javax.sql.DataSource;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AbstractTest.Config.class, webEnvironment = RANDOM_PORT)
public abstract class AbstractTest {

    @Configuration
    @ComponentScan
    @EnableCaching
    @EnableAutoConfiguration
    public static class Config {

        @Bean
        public static DataSourceProxyCreator dataSourceProxyCreator() {
            return new DataSourceProxyCreator();
        }

        public static class DataSourceProxyCreator implements BeanPostProcessor {

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof DataSource) {
                    return ProxyDataSourceBuilder.create(beanName, (DataSource) bean)
                            .countQuery()
                            .build();
                }
                return bean;
            }

        }

    }

}