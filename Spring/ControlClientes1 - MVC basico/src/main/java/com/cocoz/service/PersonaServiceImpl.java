
package com.cocoz.service;

import com.cocoz.dao.PersonaDao;
import com.cocoz.domain.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* 
Le indicamos a Spring que esta clase es un servicio => va a estar disponible 
para inyectarse 
*/
@Service
public class PersonaServiceImpl implements PersonaService{

/* Los metodos de abajo son transaccionales en tanto llaman a metodos transaccionales 
    por eso la notacion @Transactional
*/
    
    @Autowired
    private PersonaDao personaDao;
    
    @Override
    @Transactional(readOnly = true) //literalmente solo lee
    public List<Persona> listarPersonas() {
        /* FindAll retorna un tipo Object */
        return (List<Persona>) this.personaDao.findAll();
    }

    @Override
    @Transactional //modifica informacion en la DDBB
    public void guardar(Persona persona) {
        this.personaDao.save(persona);
    }

    @Override
    @Transactional //modifica informacion en la DDBB
    public void eliminar(Persona persona) {
        this.personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true) //literalmente solo lee
    public Persona encontrarPersona(Persona persona) {
/* findById retorna Optional<Persona>, por lo que podemos aclararle que hacer en 
    caso de que no recupere lo que buscamos, en este caso le indicamos que devuelva 
    null */
        return this.personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
