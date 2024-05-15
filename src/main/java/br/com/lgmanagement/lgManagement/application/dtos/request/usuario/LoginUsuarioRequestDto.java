package br.com.lgmanagement.lgManagement.application.dtos.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioRequestDto(
        @Email
        String email,
        @NotBlank
        String password
) {
}
