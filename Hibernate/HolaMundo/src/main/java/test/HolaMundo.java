package test;

import java.util.List;
import jakarta.persistence.*;
import mx.com.gm.domain.Persona;

public class HolaMundo {

    public static void main(String[] args) {
        
        String hql="SELECT p FROM Persona p";
        System.out.println(Persistence.createEntityManagerFactory("HibernatePU"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("HibernatePU");
        EntityManager manager = factory.createEntityManager();
        
        Query query = manager.createQuery(hql);
        
        List<Persona> personas = query.getResultList();
        
        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }
}
