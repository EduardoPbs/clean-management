package br.com.lgmanagement.lgManagement.domain.entities.estoque_minimo;

import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

import java.math.BigDecimal;

public class EstoqueMinimo {

    private String id;
    private Produto produto;
    private BigDecimal estoque_minimo;

    public EstoqueMinimo() {
    }

    public EstoqueMinimo(String id, Produto produto, BigDecimal estoque_minimo) {
        this.id = id;
        this.produto = produto;
        this.estoque_minimo = estoque_minimo;
    }

    public String getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(BigDecimal estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }
}
