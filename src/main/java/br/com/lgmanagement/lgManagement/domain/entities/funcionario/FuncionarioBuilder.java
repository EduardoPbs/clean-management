package br.com.lgmanagement.lgManagement.domain.entities.funcionario;

import br.com.lgmanagement.lgManagement.domain.entities.Endereco;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;

import java.util.ArrayList;

public class FuncionarioBuilder {

    private Funcionario funcionario;

    public Funcionario withNomeAndCPF(String nome, String cpf) {
        this.funcionario = new Funcionario(nome, cpf, new Endereco(), new Usuario(), new ArrayList<>());
        return this.funcionario;
    }

    public void includeEndereco(String rua, String bairro, String numero, String complemento) {
        this.funcionario.setEndereco(new Endereco(rua, bairro, numero, complemento));
    }
}
