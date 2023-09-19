
package com.cocoz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
/* Un usuario -> muchos roles (FetchType mantiene la sesion abierta mientras se recuperan los roles)
    (fetch = FetchType.EAGER)-> cambiamos a transaccional el metodo que recupera el usuario en servicio  */
    @OneToMany
    @JoinColumn(name="id_usuario") //indicamos el foreignkey
    private List<Rol> roles;
    
}