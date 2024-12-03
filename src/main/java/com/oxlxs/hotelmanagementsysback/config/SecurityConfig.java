package com.oxlxs.hotelmanagementsysback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors((cors)->cors.configurationSource(configSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()
                )
                ;
        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource configSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedHeader("Refresh-Token");
        config.addAllowedMethod("*");
        config.setExposedHeaders(List.of(
                "Access-Token",
                "Refresh-Token",
                "ID"
        ));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
