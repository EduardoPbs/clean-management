package br.com.lgmanagement.lgManagement.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    public static final List<String> WHITELIST = Arrays.asList(
            "/actuator/*",
            "/configuration/security",
            "/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-resources/configuration/security",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/webjars/swagger-ui/**",
            "/error",
            "/auth/**"
    );

    public static final List<String> AUTHENTICATED = Arrays.asList(
            "/employees/**",
            "/commodities/**",
            "/transactions/**",
            "/cashier/**",
            "/promotions/**"
    );

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITELIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(AUTHENTICATED.toArray(String[]::new)).authenticated()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITELIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(AUTHENTICATED.toArray(String[]::new)).authenticated()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

