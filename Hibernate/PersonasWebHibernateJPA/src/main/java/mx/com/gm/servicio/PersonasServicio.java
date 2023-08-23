
package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDAO;
import mx.com.gm.domain.Persona;

public class PersonasServicio {
    
    private PersonaDAO personaDAO;

    public PersonasServicio() {
        this.personaDAO = new PersonaDAO();
    }
    
    public List<Persona> listarPersonas(){
        return this.personaDAO.listar();
    }
    
    public Persona buscar(Persona persona){
        return this.personaDAO.buscarPorId(persona);
    }
    
    public void guardar(Persona persona){        
        if(persona!=null&&persona.getId()==null){
            this.personaDAO.insertar(persona);
        } else {
            this.personaDAO.modificar(persona);
        }       
    }
    
    public void eliminar(Persona persona){
        this.personaDAO.eliminar(persona);
    }
}
