package br.com.lgmanagement.lgManagement.application.usecases.funcionario;

import br.com.lgmanagement.lgManagement.infra.controller.funcionario.request.CreateFuncionarioRequest;
import br.com.lgmanagement.lgManagement.application.gateways.funcionario.FuncionarioGateway;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;

public class CreateFuncionarioInteractor {

    private final FuncionarioGateway funcionarioGateway;

    public CreateFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public Funcionario registrarFuncionario(Funcionario funcionario) {
        return funcionarioGateway.registrarFuncionario(funcionario);
    }
}
