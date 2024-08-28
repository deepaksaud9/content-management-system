package com.deepaksaud.content_management_system.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf( (csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**","/css/index.css", "/images/**", "/js/**").permitAll()
                        .requestMatchers("/", "/home","/api/v1/blog").permitAll()
                        .requestMatchers("/rest/fetchAllBlog").permitAll()
                        .requestMatchers("/rest/**").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()) // Use basic auth for API endpoints
                .formLogin(withDefaults()) // This enables the default login page
                .logout(LogoutConfigurer::permitAll);
                /*.logout((logout) -> logout.permitAll());*/

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
