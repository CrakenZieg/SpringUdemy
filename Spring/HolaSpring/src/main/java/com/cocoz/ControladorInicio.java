
package com.cocoz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j //Incluimos el spring-log4j
public class ControladorInicio {
    
    @GetMapping("/")    
    public String inicio(){
        log.info("Ejec: controlador REST '/'");
        return "<h1 style='color:red'>Hola con Spring!</h1>";
    }
    
}
