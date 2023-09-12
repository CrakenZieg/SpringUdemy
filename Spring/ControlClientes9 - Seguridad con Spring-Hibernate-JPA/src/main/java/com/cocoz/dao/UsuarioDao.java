
package com.cocoz.dao;

import com.cocoz.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario,Long>{
    // Spring va a crear la implementacion automaticamente
    Usuario findByUsername(String username);    
}
