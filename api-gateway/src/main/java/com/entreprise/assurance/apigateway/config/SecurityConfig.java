package com.entreprise.assurance.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/entreprise/**").permitAll()
                .pathMatchers("/graphql").permitAll()
                .pathMatchers("/graphiql").permitAll()
                .anyExchange().authenticated()
            )
            .httpBasic(basic -> {});
        
        return http.build();
    }
} 