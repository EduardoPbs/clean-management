package br.com.lgmanagement.lgManagement.application.usecases.funcionario;

import br.com.lgmanagement.lgManagement.application.gateways.funcionario.FuncionarioGateway;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;

public class ShowOneFuncionarioInteractor {

    private final FuncionarioGateway funcionarioGateway;

    public ShowOneFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public Funcionario mostrarFuncionario(String id) {
        return funcionarioGateway.mostrarFuncionario(id);
    }
}
