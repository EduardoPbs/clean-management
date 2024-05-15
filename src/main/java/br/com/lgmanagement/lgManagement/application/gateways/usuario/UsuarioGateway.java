package br.com.lgmanagement.lgManagement.application.gateways.usuario;

import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.LoginUsuarioRequestDto;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;

public interface UsuarioGateway {

    Usuario registrarUsuario(Usuario usuario);

    String loginUsuario(LoginUsuarioRequestDto usuarioRequestDto);

    void deletarUsuario(String id);
}
