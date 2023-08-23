
package mx.com.gm.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonasServicio;

@WebServlet("/ServletNuevaPersona")
public class ServletNuevaPersona extends HttpServlet{
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/WEB-INF/nuevaPersona.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        
        PersonasServicio personasServicio = new PersonasServicio();
        String Submit = request.getParameter("Submit");
        
        if(Submit == "Agregar"){
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");            
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");            
            Persona persona = new Persona();
            persona.setNombre(nombre);
            persona.setApellido(apellido);
            persona.setEmail(email);
            persona.setTelefono(telefono);
            personasServicio.guardar(persona);
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