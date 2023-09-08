
package com.cocoz.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Slf4j 
public class ControladorInicio {

    @GetMapping("/")
    public String inicio(Model model){//por inyeccion de dependencias model ya esta disponible
        
        var mensaje = "Hola Mundo con Thymeleaf";//creamos una variable
        model.addAttribute("mensaje", mensaje);//la agregamos al map de model
     
        return "index";
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
