
package com.cocoz.service;

import com.cocoz.dao.UsuarioDao;
import com.cocoz.domain.Rol;
import com.cocoz.domain.Usuario;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
La notacion service combierte la clase en un Bean de Spring y darle el nombre
especifico de 'userDetailsService' permite que la seguridad de Spring lo reconozca
y pueda utilizar
Justamente implementa la interfaz UserDetailsService
 */
@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if(usuario==null){
            throw new UsernameNotFoundException(username);
        }
/*
    Envolvemos los roles en GrantedAuthority (SimpleGrantedAuthority la implementa) .
    para que funcione la implementacion de JpaRepository que nosotros utilizamos
*/
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
    //retornamos un usuario de Spring
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
    
    
    
}
