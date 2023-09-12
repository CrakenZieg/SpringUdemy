
package com.cocoz.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncriptarPass {
    
//    public static void main(String[] args) {
//        
//        String pass = "paspado";
//        
//        System.out.println("paspado: " + encriptar(pass));
//        
//    }
    
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    public static String encriptar(String pass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }
}

/*
paspado: $2a$10$XOxKDt53MSjTI93O8IW9ZOnpd.ZD.UpJTInAkCuisN14TfcQRrFda

admin: $2a$10$qdAnHjNB98kk7SnCs3DVSeh4F2m3Z8E2TydHETadRQNw1yMojRJ6K

user: $2a$10$LLy4j9BGw4NP1S96iYgZPuoAJrXaxeo65P3e/.vVV03dHQJ0Qdjo.
*/
