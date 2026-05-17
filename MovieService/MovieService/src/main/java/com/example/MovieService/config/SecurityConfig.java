package com.example.MovieService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
            .exceptionHandling(ex ->
                ex.authenticationEntryPoint(unauthorizedHandler)
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // Public API pathways
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/movies/all").permitAll()
                .requestMatchers("/shows/all").permitAll()
                .requestMatchers("/shows/movie/**").permitAll()
                
                // 🔥 FIXED: Allow public access to system errors so Spring reveals the true exception
                .requestMatchers("/error").permitAll() 
                
                // Block modifying endpoints for unauthenticated calls
                .requestMatchers("/movies/**").authenticated()
                .requestMatchers("/shows/**").authenticated()
            );

        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}