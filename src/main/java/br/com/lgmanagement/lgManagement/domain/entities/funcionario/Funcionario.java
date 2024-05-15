package br.com.lgmanagement.lgManagement.domain.entities.funcionario;

import br.com.lgmanagement.lgManagement.domain.entities.Endereco;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class Funcionario {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private Usuario usuario;
    private List<Transacao> transacaos;
    private Boolean ativo;


    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, Endereco endereco, Usuario usuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.usuario = usuario;
        this.ativo = true;
    }

    public Funcionario(String nome, String cpf, Endereco endereco, Usuario usuario, List<Transacao> transacaos) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.usuario = usuario;
        this.transacaos = transacaos;
        this.ativo = true;
    }

    public void updateFuncionario(
            String nome,
            String cpf,
            Endereco updateEndereco,
            List<Transacao> transacaos,
            Usuario updateUsuario
    ) {
        if (nome != null) {
            this.nome = nome;
        }

        if (cpf != null) {
            this.cpf = cpf;
        }

        if (updateEndereco != null) {
            this.endereco.setRua(updateEndereco.getRua());
            this.endereco.setBairro(updateEndereco.getBairro());
            this.endereco.setNumero(updateEndereco.getNumero());
            this.endereco.setComplemento(updateEndereco.getComplemento());
        }

        if (transacaos != null) {
            this.transacaos = transacaos;
        }

        if (updateUsuario != null) {
            this.usuario.setEmail(updateUsuario.getEmail());
            this.usuario.setPassword(updateUsuario.getPassword());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getIsAtivo() {
        return ativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Transacao> getVendas() {
        return transacaos;
    }

    public void setVendas(List<Transacao> transacaos) {
        this.transacaos = transacaos;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", usuario=" + usuario +
                ", ativo=" + ativo +
                '}';
    }
}
