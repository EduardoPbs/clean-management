package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.LoginUsuarioRequestDto;
import br.com.lgmanagement.lgManagement.application.gateways.usuario.UsuarioGateway;
import br.com.lgmanagement.lgManagement.config.jwt.TokenService;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioRepository;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntityMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class UsuarioRepositoryGateway implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper mapper;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UsuarioRepositoryGateway(
            UsuarioRepository usuarioRepository,
            UsuarioEntityMapper mapper,
            TokenService tokenService,
            AuthenticationManager authenticationManager
    ) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        String encryptedPassword = entity.passwordEncoder(usuario.getPassword());
        entity.setPassword(encryptedPassword);
        usuarioRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public String loginUsuario(LoginUsuarioRequestDto usuarioRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        usuarioRequestDto.email(), usuarioRequestDto.password()
                );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return tokenService.gerarToken((UsuarioEntity) authentication.getPrincipal());
    }

    @Override
    public void deletarUsuario(String id) {
        usuarioRepository.deleteById(id);
    }
}
