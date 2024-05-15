package br.com.lgmanagement.lgManagement.infra.controller.funcionario.request;

import br.com.lgmanagement.lgManagement.application.dtos.EnderecoDto;
import br.com.lgmanagement.lgManagement.application.dtos.request.usuario.RegistrarUsuarioRequestDto;
import br.com.lgmanagement.lgManagement.infra.persistence.interfaces.itransacao.ITransacaoEntity;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CreateFuncionarioRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @Pattern(regexp = "^.{14}$", message = "CPF deve conter 14 caracteres.")
        @NotBlank
        String cpf,

        @Valid
        EnderecoDto endereco,

        @Valid
        @JsonAlias("usuario")
        RegistrarUsuarioRequestDto usuarioRequestDto,

        List<ITransacaoEntity> vendaList
) {
}
