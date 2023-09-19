package com.cocoz.web;

import com.cocoz.util.EncriptarPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private EncriptarPass encriptarPass;
    
    @Autowired 
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(encriptarPass.passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/editar/**", "/agregar/**",
                                "/eliminar/**", "/guardar","/webjars/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/", "/errores/**").hasAnyRole("ADMIN", "USER")                        
        ).formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
        ).exceptionHandling((exception) -> exception
                .accessDeniedPage("/errores/403"))
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}

/*

Metodo para crear usuarios en memoria
@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("paspado")
                .roles("ADMIN", "USER")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user")
                .password("paspado")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

*/
