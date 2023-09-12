
package com.cocoz.web;

import com.cocoz.domain.Persona;
import com.cocoz.service.PersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
    public String inicio(Model model, @AuthenticationPrincipal User user){//por inyeccion de dependencias model ya esta disponible
        
        var personas = personaService.listarPersonas();
        log.info("Ejecucion controlador SpringMVC");
        log.info("Loggin de usuario: "+user);
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
    public String guardar(@Valid Persona persona, Errors errores){
/*  por inyeccion de dependencias Spring va a buscar un obj persona ya que lo 
    incluimos como parametro del metodo. En este caso Spring va a usar el obj que
    estaba asociado al formulario
*/  
        if(errores.hasErrors()){//Accedemos a los errores y en cosa de haberlos vamos a editar
            return "modificar";
        }
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

13. Agregamos validaciones anotando @NotEmpty en los atributos de nuestro obj modelo
de manera que no sean nulos ni vacios, luego agregamos los span's donde agregar 
el mensaje correspondiente al error en el html del formulario

14. Agregamos la notacion @Valid en el parametro del metodo guardar del controlador
y tambien un parametro de tipo Errors para, en caso de existir, redireccionar a edicion

15. Usamos validaciones de Jakarta y debemos agregar entre las dependencias spring boot
starter validation para que funcione

16. Creamos un html que funciona de fuente de fragmentos para ser usados como reemplazo 
donde lo indiquemos (cambio la sintaxis => ~{path/pag.html :: fragmento})

17. creamos un archivo .properties para incluir texto generico con el objetivo de 
internacionalizar

18. Podemos incluir texto que responda especificamente a los errores producto de 
validacion, por ejemplo si el formulario posee un campo anotado como NotEmpty en 
el modelo y se intenta hacer submit

19. Creamos en el paquete web una clase WebConfig para incluir un listener que recupere
el idioma del navegador y en funcion de este utilice un archivo de lenguaje u otro.
WebConfig implementa la interface WebMvcConfigurer, debe estar anotada como @Configuration

20. Lo primero que definimos es un Bean llamado localResolver que, como indica el 
nombre, resuelve 'local' para el navegador. Luego otro Bean localeChangeInterceptor
que agrega un interceptor ante una ruta de parametro lang. Por ultimo sobreescribimos 
addInterceptors al que se le inyecta InterceptorsRegistry y al cual aniadimos el 
interceptor del segundo paso.

21. Agregamos las copias de messages.properties, messages_es.properties y messages_en.properties  
la original queda, las otras se modifican (si es necesario) con la traduccion a los
idiomas que agreguemos (en en ingles, de deutsch, fr francais, etc)

22. Agregamos la libreria de spring-boot-starter-security, automaticamente se genera
un user y un pass que nos muestra por log

23. Creamos la clase SecurityConfig que anotaremos con @Configuration y @EnableWebSecurity
y debe extender de WebSecurityConfigurarAdapter => nope, deprecado:
https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

24. Creamos un metodo que devuelve un usuario en memoria(hardcodeado) anotado como 
@Bean (lo que lo deja disponible a Spring) ya que esta en una clase anotada como @Configuration

25. Agregamos un boton de logout en el footer (en layout/plantilla), dentro de un form, 
con method post y action @{/logout}

26. En SecurityConfig agregamor el metodo filterChain que devuelve obj SecurityFilterChain
que posee las request autenticadas o no y para que rol de autorizacion

27. Agregamos en WebConfig un override al metodo addViewControllers y agregamos al
registro las views/paths, y sus nombres

28. Creamos un login.html y agregamos la view en WebConfig

29. Agregamos un package de errores para guardar los html correspondientes, creamos 
un html para 403 y lo agregamos a WebConfig y a SecurityConfig (como ExceptionHandling())
*tambien agregamos el path de los erroress a authorizeHttpRequests

30. Podemos recuperar el usuario que se logeo inyectando @AuthenticationPrincipal User user
en los parametros de get '/' (o en cualquier otro lado claro esta)

31. Agregando la libreria de security de thymeleaf (en pom y como prefijos en html) 
podemos acceder a datos de authority desde nuestros html, con lo que podemos modificar
las views, por ejemplo

*/
