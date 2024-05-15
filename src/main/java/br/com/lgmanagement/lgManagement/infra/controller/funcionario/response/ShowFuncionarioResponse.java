package br.com.lgmanagement.lgManagement.infra.controller.funcionario.response;

import br.com.lgmanagement.lgManagement.domain.entities.Endereco;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.ShowUsuarioResponse;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;

public record ShowFuncionarioResponse(
        String id,
        String nome,
        String cpf,
        Endereco endereco,
        Boolean ativo,
        ShowUsuarioResponse usuario
) {
    public ShowFuncionarioResponse(FuncionarioEntity funcionarioEntity) {
        this(
                funcionarioEntity.getId(),
                funcionarioEntity.getNome(),
                funcionarioEntity.getCpf(),
                funcionarioEntity.getEndereco(),
                funcionarioEntity.getIsAtivo(),
                new ShowUsuarioResponse(funcionarioEntity.getUsuarioEntity())
        );
    }
}
