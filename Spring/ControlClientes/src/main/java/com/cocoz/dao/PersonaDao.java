
package com.cocoz.dao;

import com.cocoz.domain.Persona;
import org.springframework.data.repository.CrudRepository;

/* 
en vez de anotarla con @Repository, extendemos de crudRepository y le indicamos 
la clase entidad y el tipo de su primaryKey. Esta interfaz no necesita implementacion
como realizabamos antes, ya que Spring la crea automaticamente.
Aqui mismo podemos indicar mas metodos que los proveidos por el crud
*/
public interface PersonaDao extends CrudRepository<Persona, Long>{
    
}
