package br.com.lgmanagement.lgManagement.application.dtos.request.usuario;

import jakarta.validation.constraints.Email;

public record AtualizarUsuarioRequestDto(
        String email,
        String password
) {
}
