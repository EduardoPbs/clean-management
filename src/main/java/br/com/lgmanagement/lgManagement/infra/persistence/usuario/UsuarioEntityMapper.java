package br.com.lgmanagement.lgManagement.infra.persistence.usuario;

import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.RegistrarUsuarioRequestDto;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioEntityMapper {

    private final UsuarioRepository usuarioRepository;

    public UsuarioEntityMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * RESOLVER AQUI ENCONTRAR O USUARIO
     */
    public UsuarioEntity toEntity(Usuario usuario) {
        UserDetails existsUsuario = usuarioRepository
                .findByEmail(usuario.getEmail());

        if (existsUsuario != null && existsUsuario instanceof UsuarioEntity) {
            return new UsuarioEntity(
                    ((UsuarioEntity) existsUsuario).getId(),
                    existsUsuario.getUsername(),
                    existsUsuario.getPassword(),
                    usuario.isAdmin()
            );
        }

        return new UsuarioEntity(
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.isAdmin()
        );
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getEmail(),
                entity.getPassword()
        );
    }

    public UsuarioEntity fromDtoToEntity(RegistrarUsuarioRequestDto usuarioRequestDto) {
        return new UsuarioEntity(
                usuarioRequestDto.email(),
                usuarioRequestDto.password(),
                usuarioRequestDto.isAdmin()
        );
    }
}
