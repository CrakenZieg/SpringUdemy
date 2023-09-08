
package com.cocoz.domain;

import lombok.Data;

@Data //lombok nos crea todos los metodos comunes de una clase de dominio (un bean)
public class Persona {
    
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    
}
