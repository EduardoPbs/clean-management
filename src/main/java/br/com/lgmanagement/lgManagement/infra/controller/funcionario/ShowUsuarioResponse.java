package br.com.lgmanagement.lgManagement.infra.controller.funcionario;

import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ShowUsuarioResponse(
        String id,
        String email,
        @JsonProperty("is_admin")
        Boolean isAdmin
) {
    public ShowUsuarioResponse(UsuarioEntity usuarioEntity) {
        this(usuarioEntity.getId(), usuarioEntity.getEmail(), usuarioEntity.getIsAdmin());
    }
}
