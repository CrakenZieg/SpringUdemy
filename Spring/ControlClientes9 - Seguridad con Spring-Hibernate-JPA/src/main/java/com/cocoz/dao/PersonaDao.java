
package com.cocoz.dao;

import com.cocoz.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/* 
en vez de anotarla con @Repository, extendemos de crudRepository y le indicamos 
la clase entidad y el tipo de su primaryKey. Esta interfaz no necesita implementacion
como realizabamos antes, ya que Spring la crea automaticamente.
Aqui mismo podemos indicar mas metodos que los proveidos por el crud

Cambiamos por JpaRepository ya que extiende de una de pagination (que extiende de
crud) y ademas extiende tambien de una clase que incluye querys que se hacen en 
batch
*/
public interface PersonaDao extends JpaRepository<Persona, Long>{
    
}
