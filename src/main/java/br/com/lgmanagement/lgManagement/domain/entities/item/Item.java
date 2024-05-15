package br.com.lgmanagement.lgManagement.domain.entities.item;

import br.com.lgmanagement.lgManagement.domain.entities.interfaces.iItemTransacao.IItemTransacao;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.math.BigDecimal;
import java.util.UUID;

public class Item implements IItemTransacao {

    private String id;
    private BigDecimal valorUnitario;
    private BigDecimal quantidade;
    private Produto produto;
    private Transacao transacao;

    public Item() {
    }

    public Item(
            BigDecimal quantidade,
            Produto produto
    ) {
        this.id = UUID.randomUUID().toString();
        this.valorUnitario = produto.getValor();
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Item(
            String id,
            BigDecimal valorUnitario,
            BigDecimal quantidade,
            Produto produto,
            Transacao transacao
    ) {
        this.id = id;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.produto = produto;
        this.transacao = transacao;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public BigDecimal getValorUnitario() {
        return this.valorUnitario;
    }

    @Override
    public BigDecimal getQuantidade() {
        return this.quantidade;
    }

    @Override
    public Produto getProduto() {
        return this.produto;
    }

    @Override
    public Transacao getTransacao() {
        return this.transacao;
    }

    @Override
    public BigDecimal calcularTotal() {
        return this.quantidade.multiply(this.valorUnitario);
    }

    @Override
    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    @Override
    public String toString() {
        return "Item{" +
                "valorUnitario=" + valorUnitario +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                ", transacao=" + transacao +
                '}';
    }
}
