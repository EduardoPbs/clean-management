package br.com.lgmanagement.lgManagement.application.usecases.funcionario;

import br.com.lgmanagement.lgManagement.application.gateways.funcionario.FuncionarioGateway;

public class DeleteFuncionarioInteractor {

    private final FuncionarioGateway funcionarioGateway;

    public DeleteFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public void deletarFuncionario(String id) {
        funcionarioGateway.deletarFuncionario(id);
    }
}
