package mx.com.gm.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import mx.com.gm.domain.Persona;

public class PersonaDAO {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public PersonaDAO(){
        emf = Persistence.createEntityManagerFactory("HibernatePU");
        em = emf.createEntityManager();
    }
    
    public List<Persona> listar(){
        String hql = "SELECT p FROM Persona p";
        Query query = em.createQuery(hql);
        List<Persona> personas = query.getResultList();
        return personas;
    }
    
    public void insertar(Persona persona){
        try{
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace(System.out);
            em.getTransaction().rollback();
//        }finally{
//            if(em!=null){
//                em.close();
//            }
        }
    }
        
    public Persona buscarPorId(Persona persona){
        return em.find(Persona.class, persona.getId());
    }
    
    public void modificar(Persona persona){
        try{
            em.getTransaction().begin();
            em.merge(persona);
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace(System.out);
            em.getTransaction().rollback();
//        }finally{
//            if(em!=null){
//                em.close();
//            }
        }
    }
    
    public void eliminar(Persona persona){
        try{
            em.getTransaction().begin();
            em.remove(em.merge(persona));
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace(System.out);
            em.getTransaction().rollback();
//        }finally{
//            if(em!=null){
//                em.close();
//            }
        }
    }
    
    
}
