
package mx.com.gm.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonasServicio;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        PersonasServicio personasServicio = new PersonasServicio();
        
        List<Persona> personas = personasServicio.listarPersonas();
        
        request.setAttribute("personas", personas);
        
        try {
            request.getRequestDispatcher("/WEB-INF/listado.jsp").forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
