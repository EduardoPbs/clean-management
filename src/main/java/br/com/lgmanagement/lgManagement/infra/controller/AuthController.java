package br.com.lgmanagement.lgManagement.infra.controller;

import br.com.lgmanagement.lgManagement.application.dtos.response.TokenResponseDto;
import br.com.lgmanagement.lgManagement.application.usecases.usuario.LoginUsuario;
import br.com.lgmanagement.lgManagement.application.usecases.usuario.RegistrarUsuario;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.LoginUsuarioRequestDto;
import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.RegistrarUsuarioRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrarUsuario registrarUsuario;
    private final LoginUsuario loginUsuario;

    public AuthController(RegistrarUsuario registrarUsuario, LoginUsuario loginUsuario) {
        this.registrarUsuario = registrarUsuario;
        this.loginUsuario = loginUsuario;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrarUsuarioRequestDto usuarioRequest) {
        registrarUsuario.registrarUsuario(
                new Usuario(usuarioRequest.email(), usuarioRequest.password()));
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid LoginUsuarioRequestDto usuarioRequest) {
        final String tokenJwt = loginUsuario.fazerLogin(usuarioRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponseDto(tokenJwt));
    }
}
