package br.com.lgmanagement.lgManagement.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UsuarioResponseDto(
        String email,
        @JsonAlias("is_admin")
        Boolean isAdmin
) {
}
