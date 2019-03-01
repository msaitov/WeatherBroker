package ru.msaitov.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Инициализация диспатчер сервлета
 */
public class DispatcherInitialization implements WebApplicationInitializer {

    /**
     * В данном методе поднимаем контекст Spring MVC
     * @param servletContext
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);
        context.refresh();

        ServletRegistration.Dynamic registration = servletContext.addServlet("dispInit", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}