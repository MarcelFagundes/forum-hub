package com.challenge.forum_hub.forum_hub.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static javax.management.Query.and;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return
//                http.csrf(csrf -> csrf.disable())
//                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                        .authorizeHttpRequests(req -> {
//                            req.requestMatchers("/login").permitAll();
//                            req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
//                            req.anyRequest().authenticated();
//                        })
//                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//                        .build();
//    }



    @Configuration
    public class SecurityConfig {

        private final SecurityFilter securityFilter;

        public SecurityConfig(SecurityFilter securityFilter) {
            this.securityFilter = securityFilter;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                    .sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST, "/login").permitAll() // Permite POST em /login
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll() // Permite Swagger
                            .anyRequest().authenticated() // Todas as outras rotas requerem autenticação
                    )
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro customizado

            return http.build();
        }
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}