package br.com.lgmanagement.lgManagement.infra.persistence.promocao;

import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "promocoes")
@Entity
@EqualsAndHashCode(of = "id")
public class PromocaoEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produtoEntity;
    private Boolean ativo;
    private BigDecimal porcentagemDesconto;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public PromocaoEntity() {
    }

    public PromocaoEntity(
            ProdutoEntity produtoEntity,
            BigDecimal porcentagemDesconto,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        this.id = UUID.randomUUID().toString();
        this.produtoEntity = produtoEntity;
        this.ativo = false;
        this.porcentagemDesconto = porcentagemDesconto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public PromocaoEntity(
            String id,
            ProdutoEntity produtoEntity,
            Boolean ativo,
            BigDecimal porcentagemDesconto,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        this.id = id;
        this.produtoEntity = produtoEntity;
        this.ativo = ativo;
        this.porcentagemDesconto = porcentagemDesconto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Boolean active() {
        this.setAtivo(Boolean.TRUE);
        return Boolean.TRUE;
    }

    public Boolean disable() {
        this.setAtivo(Boolean.FALSE);
        return Boolean.FALSE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProdutoEntity getProdutoEntity() {
        return produtoEntity;
    }

    public void setProdutoEntity(ProdutoEntity produtoEntity) {
        this.produtoEntity = produtoEntity;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(BigDecimal porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "PromocaoEntity{" +
                "id='" + id + '\'' +
                ", produtoEntity='" + produtoEntity + '\'' +
                ", ativo=" + ativo +
                ", porcentagemDesconto=" + porcentagemDesconto +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }
}
