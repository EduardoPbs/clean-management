package br.com.lgmanagement.lgManagement.application.usecases.funcionario;

import br.com.lgmanagement.lgManagement.application.gateways.funcionario.FuncionarioGateway;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;

import java.util.List;

public class ShowAllFuncionariosInteractor {

    private final FuncionarioGateway funcionarioGateway;

    public ShowAllFuncionariosInteractor(FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioGateway.listarFuncionarios();
    }
}
