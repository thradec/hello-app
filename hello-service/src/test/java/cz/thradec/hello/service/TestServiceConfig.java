package cz.thradec.hello.service;

import javax.sql.DataSource;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ServiceConfig.class)
public class TestServiceConfig {

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