package br.com.lgmanagement.lgManagement.application.dtos.request.usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrarUsuarioRequestDto(
        @Email(message = "Informe um email válido.")
        @NotBlank(message = "Email é obrigatório.")
        String email,

        @NotBlank(message = "Senha é obrigatório.")
        String password,

        @JsonAlias("is_admin")
        Boolean isAdmin
) {
}
