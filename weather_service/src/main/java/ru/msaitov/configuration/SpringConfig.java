package ru.msaitov.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.msaitov.hessian.Communication;

/**
 * Конфигурация Spring MVC
 */
@Configuration
@ImportResource("classpath:config.xml")
@ComponentScan("ru.msaitov.controller")
public class SpringConfig implements WebMvcConfigurer {

    /**
     * Фабрика Hessian
     * @return
     */
    @Bean
    public HessianProxyFactoryBean exporter() {
        HessianProxyFactoryBean hessianProxyFactoryBean = new HessianProxyFactoryBean();
        hessianProxyFactoryBean.setServiceUrl("http://localhost:8080/dbService-1.0-SNAPSHOT/service");
        hessianProxyFactoryBean.setServiceInterface(Communication.class);
        return hessianProxyFactoryBean;
    }
}
