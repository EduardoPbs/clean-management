package br.com.lgmanagement.lgManagement.application.usecases.funcionario;

import br.com.lgmanagement.lgManagement.application.gateways.funcionario.FuncionarioGateway;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.request.UpdateFuncionarioRequest;

public class UpdateFuncionarioInteractor {

    private final FuncionarioGateway funcionarioGateway;

    public UpdateFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public Funcionario updateFuncionario(String id, Funcionario funcionario) {
        return funcionarioGateway.atualizarFuncionario(id, funcionario);
    }
}
