
package com.cocoz;

import com.cocoz.domain.Persona;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller//vamos a usar SpringMVC
@Slf4j //Incluimos el spring-log4j
public class ControladorInicio {
    
    //recuperamos el valor de application.properties y se inyecta gracias a Value
    @Value("${index.mensaje}")
    private String mensajeProp;
    
    @GetMapping("/")
    public String inicio(Model model){//por inyeccion de dependencias model ya esta disponible
        
        var mensaje = "Hola Mundo con Thymeleaf";//creamos una variable
        model.addAttribute("mensaje", mensaje);//la agregamos al map de model
        
        model.addAttribute("mensajeProp", mensajeProp);
        
        var persona1 = new Persona();
        persona1.setNombre("Mau1");
        persona1.setApellido("Peludo1");
        persona1.setEmail("peludo1@mau.com");
        persona1.setTelefono("123456987");
        var persona2 = new Persona();
        persona2.setNombre("Mau2");
        persona2.setApellido("Peludo2");
        persona2.setEmail("peludo2@mau.com");
        persona2.setTelefono("123456987");
        log.info("Ejec: controlador Spring MVC");        
        
        List<Persona> personas = new ArrayList<>();
        personas.add(persona1);
        personas.add(persona2);
        model.addAttribute("personas", personas);
        
        //el attribute 'mensaje' va a estar disponible en el index mediante el model
        return "index";//como es mvc ahora devolvemos directamente la pagina a cargar
    }
    
}
/*
Model:
Interface que permite sumarle atributos para renderizarse en las vistas, simplemente le agregamos
la informacion al obj Model, tambien se le pueden mergear maps 
(porque se le pasan los atributos como clave/valor)

ModelMap:
Idem, pero se pueden pasar varios valores (no estaría viendo la diferencia)

ModelAndView:
Este no se inyecta en los parametros del metodo, se instancia dentro de este con el path como 
parametro, luego se agregan los atributos y se retorna la instancia directamente.
*/
