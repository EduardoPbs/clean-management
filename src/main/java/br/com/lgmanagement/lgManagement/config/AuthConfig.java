package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.config.jwt.TokenService;
import br.com.lgmanagement.lgManagement.config.security.AuthService;
import br.com.lgmanagement.lgManagement.config.security.SecurityConfigurations;
import br.com.lgmanagement.lgManagement.config.security.SecurityFilter;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    SecurityFilter filter(
            TokenService tokenService,
            UsuarioRepository usuarioRepository
    ) {
        return new SecurityFilter(tokenService, usuarioRepository);
    }

    @Bean
    SecurityConfigurations configurations(SecurityFilter securityFilter) {
        return new SecurityConfigurations(securityFilter);
    }

    @Bean
    AuthService authService(UsuarioRepository usuarioRepository) {
        return new AuthService(usuarioRepository);
    }
}
