package br.com.lgmanagement.lgManagement.infra.persistence.funcionario;

import br.com.lgmanagement.lgManagement.domain.entities.Endereco;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Table(name = "funcionarios")
@Entity
@EqualsAndHashCode(of = "id")
public class FuncionarioEntity {

    @Id
    private String id;
    private String nome;

    @Column(unique = true)
    private String cpf;

    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuarioEntity;

    @OneToMany(mappedBy = "funcionarioEntity")
    private List<TransacaoEntity> vendas;

    public FuncionarioEntity() {
    }

    public FuncionarioEntity(
            String nome,
            String cpf,
            Endereco endereco,
            UsuarioEntity usuarioEntity,
            List<TransacaoEntity> vendas
    ) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.ativo = true;
        this.usuarioEntity = usuarioEntity;
        this.vendas = vendas;
    }

    public FuncionarioEntity(
            String id,
            String nome,
            String cpf,
            Endereco endereco,
            UsuarioEntity usuarioEntity,
            List<TransacaoEntity> vendas
    ) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.ativo = true;
        this.usuarioEntity = usuarioEntity;
        this.vendas = vendas;
    }

    public void delete() {
        this.ativo = false;
    }

    public void restore() {
        this.ativo = true;
    }

    public void updateFuncionario(
            String nome,
            String cpf,
            Endereco enderecoDto,
            UsuarioEntity usuarioEntity
    ) {
        if (nome != null) {
            this.nome = nome;
        }

        if (cpf != null) {
            this.cpf = cpf;
        }

        if (enderecoDto != null) {
            this.endereco.updateEndereco(
                    enderecoDto.getRua(),
                    enderecoDto.getBairro(),
                    enderecoDto.getNumero(),
                    enderecoDto.getComplemento()
            );
        }

        if (usuarioEntity != null) {
            this.usuarioEntity.updateUsuario(
                    usuarioEntity.getEmail(),
                    usuarioEntity.getPassword()
            );
        }

        if (vendas != null) {
            this.vendas = vendas;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public List<TransacaoEntity> getVendas() {
        return vendas;
    }

    public void setVendas(List<TransacaoEntity> vendas) {
        this.vendas = vendas;
    }

    @Override
    public String toString() {
        return "FuncionarioEntity{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", ativo=" + ativo +
                '}';
    }
}
