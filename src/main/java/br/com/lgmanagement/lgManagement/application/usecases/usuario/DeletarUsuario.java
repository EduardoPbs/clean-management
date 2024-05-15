package br.com.lgmanagement.lgManagement.application.usecases.usuario;

import br.com.lgmanagement.lgManagement.application.gateways.usuario.UsuarioGateway;

public class DeletarUsuario {

    private final UsuarioGateway usuarioGateway;

    public DeletarUsuario(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void deletarUsuario(String id) {
        usuarioGateway.deletarUsuario(id);
    }
}
