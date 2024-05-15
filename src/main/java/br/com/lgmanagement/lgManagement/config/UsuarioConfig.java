package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.application.gateways.usuario.UsuarioGateway;
import br.com.lgmanagement.lgManagement.application.usecases.usuario.DeletarUsuario;
import br.com.lgmanagement.lgManagement.config.jwt.TokenService;
import br.com.lgmanagement.lgManagement.application.usecases.usuario.LoginUsuario;
import br.com.lgmanagement.lgManagement.application.usecases.usuario.RegistrarUsuario;
import br.com.lgmanagement.lgManagement.infra.gateways.UsuarioRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class UsuarioConfig {

    @Bean
    RegistrarUsuario registrarUsuario(UsuarioGateway usuarioGateway) {
        return new RegistrarUsuario(usuarioGateway);
    }

    @Bean
    LoginUsuario loginUsuario(UsuarioGateway usuarioGateway) {
        return new LoginUsuario(usuarioGateway);
    }

    @Bean
    DeletarUsuario deletarUsuario(UsuarioGateway usuarioGateway) {
        return new DeletarUsuario(usuarioGateway);
    }

    @Bean
    UsuarioRepositoryGateway usuarioGateway(
            UsuarioRepository usuarioRepository,
            UsuarioEntityMapper usuarioEntityMapper,
            TokenService tokenService,
            AuthenticationManager authenticationManager
    ) {
        return new UsuarioRepositoryGateway(
                usuarioRepository,
                usuarioEntityMapper,
                tokenService,
                authenticationManager
        );
    }

    @Bean
    UsuarioEntityMapper usuarioEntityMapper(UsuarioRepository usuarioRepository) {
        return new UsuarioEntityMapper(usuarioRepository);
    }
}
