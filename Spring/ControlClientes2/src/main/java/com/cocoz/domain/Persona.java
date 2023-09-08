
package com.cocoz.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data //lombok nos crea todos los metodos comunes de una clase de dominio (un bean porque tiene serializable)
@Entity
@Table(name="persona")
public class Persona implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private double saldo;
    
}
