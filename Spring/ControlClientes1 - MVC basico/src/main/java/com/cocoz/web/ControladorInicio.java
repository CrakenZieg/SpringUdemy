
package com.cocoz.web;

import com.cocoz.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@Slf4j 
public class ControladorInicio {

/* le indica a Spring que inyecte la dependencia personaService 
    => Spring va a buscar una implementacion
*/
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    public String inicio(Model model){//por inyeccion de dependencias model ya esta disponible
        
        var personas = personaService.listarPersonas();
        
        //agregamos personas al map de model
        model.addAttribute("personas", personas);
     
        return "index";
    }
    
}
/*

Resumen:

1. Agregamos depencencias en el pom: mysql-connector-j y spring-boot-starter-data-jpa 

2. Agregamos la conexion a la DDBB en application.properties

3. Creamos clase de entidad

4. Creamos interface <Entidad>Dao y la hacemos extender de CrudRepository<Entidad,PKentidad>

5. Creamos una interface y una implementacion para el servicio y le inyectamos la interfaz DAO

6. Inyectamos la interfaz service en el controlador y la usamos para recuperar datos y redirigir llamadas

*/
