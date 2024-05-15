package br.com.lgmanagement.lgManagement.application.usecases.usuario;

import br.com.lgmanagement.lgManagement.application.gateways.usuario.UsuarioGateway;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;

public class RegistrarUsuario {

    private final UsuarioGateway usuarioGateway;

    public RegistrarUsuario(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioGateway.registrarUsuario(usuario);
    }
}
