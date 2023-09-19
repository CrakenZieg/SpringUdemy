
package com.cocoz.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
/* utilizamos el siguiente bean para recuperar el obj 'local' */
    @Bean //le indicamos a Spring que el siguiente metodo devuelve un bean
    public LocaleResolver localeResolver(){
//SessionLocaleResolver es una clase que implementa localeResolver        
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
    
/* utilizamos el siguiente bean para cambiar el obj 'local' */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
//Instanciamos un interceptor
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
//Agregamos el interceptor al registro
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
}
