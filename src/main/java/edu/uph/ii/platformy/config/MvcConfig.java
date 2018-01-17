package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.config.beans.WebMvcConfigurerImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by grzesiek on 23.09.2017.
 * Konfiguracja webowego kontekstu aplikacji
 */

@Configuration
@ComponentScan(basePackages = {"edu.uph.ii.platformy.controllers", "edu.uph.ii.platformy.exceptions"}, basePackageClasses = WebMvcConfigurerImpl.class)
@EnableWebMvc
@EnableGlobalMethodSecurity(securedEnabled = true)//tą adnotację można też użyć w warstwie biznesowej (JpaConfig)
@EnableSpringDataWebSupport
//@EnableTransactionManagement
@Import(RepositoriesInitializer.class)
public class MvcConfig{

    @Bean(name = "messageSource")
    public MessageSource reloadableResourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("/WEB-INF/error-messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r =
                new SimpleMappingExceptionResolver();

//        Properties mappings = new Properties();
//        mappings.setProperty(AccessDeniedException.class.getSimpleName(), "databaseError");
//        mappings.setProperty(Exception.class.getSimpleName(), "defaultErrorView");
//
//        r.setExceptionMappings(mappings);  // Domyślnie brak
        r.setDefaultErrorView("errors/defaultErrorView");    // Domyślnie brak
        r.setExceptionAttribute("exception");     // Domyślna nazwa to "exception"
        r.setWarnLogCategory("example.MvcLogger");     // Domyślnie brak
        return r;
    }

}

