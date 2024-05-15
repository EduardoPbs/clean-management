package br.com.lgmanagement.lgManagement.application.usecases.usuario;

import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.LoginUsuarioRequestDto;
import br.com.lgmanagement.lgManagement.application.gateways.usuario.UsuarioGateway;

public class LoginUsuario {

    private final UsuarioGateway usuarioGateway;

    public LoginUsuario(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public String fazerLogin(LoginUsuarioRequestDto usuarioRequestDto) {
        return usuarioGateway.loginUsuario(usuarioRequestDto);
    }
}
