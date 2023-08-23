
package mx.com.gm.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonasServicio;

@WebServlet("/ServletFormulario")
public class ServletFormulario extends HttpServlet{
    
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        
        PersonasServicio personasServicio = new PersonasServicio();
        this.id = Long.valueOf(request.getParameter("id"));        
        Persona persona = new Persona(this.id);   
        persona = personasServicio.buscar(persona);
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("persona", persona);
                
        try {
            request.getRequestDispatcher("/WEB-INF/formulario.jsp").forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        
        PersonasServicio personasServicio = new PersonasServicio();
        String modificar = request.getParameter("Modificar");
        
        if(modificar != null){
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");            
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            HttpSession sesion = request.getSession();
            Persona persona = (Persona) sesion.getAttribute("persona");
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setEmail(email);
            persona.setTelefono(telefono);
            personasServicio.guardar(persona);
        } else {
            String idPersonaS = request.getParameter("idAlumno");
            Long idPersona = Long.parseLong(idPersonaS);
            Persona persona = new Persona(idPersona);
            personasServicio.eliminar(persona);
        }
        try {
            request.getRequestDispatcher("/index.html").forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
