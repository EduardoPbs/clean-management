package br.com.lgmanagement.lgManagement.application.gateways.funcionario;

import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;

import java.util.List;

public interface FuncionarioGateway {
    Funcionario registrarFuncionario(Funcionario funcionario);

    List<Funcionario> listarFuncionarios();

    Funcionario atualizarFuncionario(String id, Funcionario funcionario);

    void deletarFuncionario(String id);

    Funcionario mostrarFuncionario(String id);
}
