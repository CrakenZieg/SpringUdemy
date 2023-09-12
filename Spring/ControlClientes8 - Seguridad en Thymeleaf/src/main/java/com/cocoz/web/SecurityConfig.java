package com.cocoz.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (requests) -> requests
                        .requestMatchers("/editar/**", "/agregar/**",
                                "/eliminar/**", "/guardar")
                        .hasRole("ADMIN")
                        .requestMatchers("/","/errores/**").hasAnyRole("ADMIN", "USER")
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

*/
