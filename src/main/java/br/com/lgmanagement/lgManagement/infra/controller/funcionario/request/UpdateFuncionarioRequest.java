package br.com.lgmanagement.lgManagement.infra.controller.funcionario.request;

import br.com.lgmanagement.lgManagement.application.dtos.EnderecoDto;
import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.AtualizarUsuarioRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateFuncionarioRequest(
        String nome,
        String cpf,
        @JsonProperty("endereco")
        EnderecoDto enderecoDto,
        @JsonProperty("usuario")
        AtualizarUsuarioRequestDto atualizarUsuarioRequestDto
) {
}
