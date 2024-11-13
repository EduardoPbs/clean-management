package br.com.lgmanagement.lgManagement.infra.persistence.estoque_minimo;

import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "estoque_minimo")
@Entity
@EqualsAndHashCode(of = "id")
public class EstoqueMinimoEntity {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produtoEntity;

    @Column(precision = 38, scale = 3)
    private BigDecimal estoque_minimo;

    public EstoqueMinimoEntity() {
    }

    public EstoqueMinimoEntity(String id, ProdutoEntity produtoEntity, BigDecimal estoque_minimo) {
        this.id = id;
        this.produtoEntity = produtoEntity;
        this.estoque_minimo = estoque_minimo;
    }

    public EstoqueMinimoEntity(ProdutoEntity produtoEntity, BigDecimal estoque_minimo) {
        this.id = UUID.randomUUID().toString();
        this.produtoEntity = produtoEntity;
        this.estoque_minimo = estoque_minimo;
    }

    public String getId() {
        return id;
    }

    public ProdutoEntity getProdutoEntity() {
        return produtoEntity;
    }

    public void setProdutoEntity(ProdutoEntity produtoEntity) {
        this.produtoEntity = produtoEntity;
    }

    public BigDecimal getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(BigDecimal estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }
}
