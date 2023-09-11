
package com.cocoz.web;

import com.cocoz.domain.Persona;
import com.cocoz.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

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
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
/*  por inyeccion de dependencias Spring va a buscar un obj persona o lo va a crear
    ya que lo incluimos como parametro del metodo. Podriamos tambien crearlo dentro
    del metodo como hariamos tradicionalmente
*/
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(Persona persona){
/*  por inyeccion de dependencias Spring va a buscar un obj persona ya que lo 
    incluimos como parametro del metodo. En este caso Spring va a usar el obj que
    estaba asociado al formulario
*/  
        personaService.guardar(persona);
        return "redirect:/"; //redireccionamos al inicio
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
/*  por inyeccion de dependencias Spring va a buscar un obj persona ya que lo 
    incluimos como parametro del metodo. En este caso Spring va a usar el obj que
    tiene el idPersona que le pasamos como path variable
        
    Necesitamos a model para poder compartirla en la siguiente peticion
*/
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona",persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
/*  por inyeccion de dependencias Spring va a buscar un obj persona ya que lo 
    incluimos como parametro del metodo. En este caso Spring va a usar el obj que
    tiene el idPersona que le pasamos como path variable
*/
        personaService.eliminar(persona);        
        return "redirect:/";
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

7. Agregamos en index el link para ir a 'Agregar Persona'.

8. Luego en el controlador debemos agregar el mapping para esa peticion incluyendo 
el objeto y retornamos la vista

9. Asociamos en la vista el objeto a un form que debe incluir un path, un method y el obj 
con el que se relaciona (el que inyectamos en el metodo que nos redirecciono a esa vista)

10. Mapeamos el path del formulario en el controlador para que maneje el destino del obj
del formulario

7. Agregamos en index el link para ir a 'Editar Persona' (en cada fila de la tabla).

8. Luego en el controlador debemos agregar el mapping para esa peticion incluyendo el id 
del objeto, lo buscamos con el servicio, lo agregamos a model y retornamos la vista de
edicion, va a ser la misma que la de creacion 
-> en la de cracion crea un obj nuevo
-> en la de edicion usa el que reuperamos e incluimos en model 

9. Asociamos en la vista el objeto a un form que debe incluir un path, un method y el obj 
con el que se relaciona (el que recuperamos en el metodo que nos redirecciono a esa vista)
incluimos en el form un campo oculto con el idPersona para que Spring sepa que 
debe modificar un obj existente y no crear uno nuevo

10. Mapeamos el path del formulario en el controlador para que maneje el destino del obj
del formulario

11. Agregamos en index el link para eliminar, en vez de pasar el id concatenado 
lo pasamos como query parameter

12. Creamos un metodo analogo a editar pero solo redirecciona al index luego de 
eliminar el obj usando el servicio. Como el parameter del query es igual a uno de 
los atributos del obj, Spring automaticamente settea ese atributo en el obj

*/
