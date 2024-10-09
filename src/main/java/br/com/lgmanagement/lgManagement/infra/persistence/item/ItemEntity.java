package br.com.lgmanagement.lgManagement.infra.persistence.item;

import br.com.lgmanagement.lgManagement.infra.persistence.interfaces.iItemTransacao.IItemTransacaoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "itens")
@Entity
@EqualsAndHashCode(of = "id")
public class ItemEntity implements IItemTransacaoEntity {

    @Id
    private String id;
    private BigDecimal valorUnitario;
    private BigDecimal quantidade;

    @ManyToOne
    private ProdutoEntity produto;

    @ManyToOne
    private TransacaoEntity transacao;

    public ItemEntity() {
    }

    public ItemEntity(
            BigDecimal quantidade,
            ProdutoEntity produto,
            TransacaoEntity transacao
    ) {
        this.id = UUID.randomUUID().toString();
        this.valorUnitario = produto.getValor();
        this.quantidade = quantidade;
        this.produto = produto;
        this.transacao = transacao;
    }

    public ItemEntity(
            BigDecimal quantidade,
            ProdutoEntity produto,
            TransacaoEntity transacao,
            Boolean isEntrance
    ) {
        this.id = UUID.randomUUID().toString();
        if (isEntrance) {
            this.valorUnitario = produto.getValorCompra();
        } else {
            this.valorUnitario = produto.getValor();
        }
        this.quantidade = quantidade;
        this.produto = produto;
        this.transacao = transacao;
    }

    public ItemEntity(
            BigDecimal quantidade,
            ProdutoEntity produto
    ) {
        this.id = UUID.randomUUID().toString();
        this.valorUnitario = produto.getValor();
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public ItemEntity(
            String id,
            BigDecimal quantidade,
            ProdutoEntity produto
    ) {
        this.id = id;
        this.valorUnitario = produto.getValor();
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public ItemEntity(
            String id,
            BigDecimal quantidade,
            ProdutoEntity produto,
            TransacaoEntity transacao
    ) {
        this.id = id;
        this.valorUnitario = produto.getValor();
        this.quantidade = quantidade;
        this.produto = produto;
        this.transacao = transacao;
    }

    public ItemEntity(
            String id,
            BigDecimal valorUnitario,
            BigDecimal quantidade,
            ProdutoEntity produto,
            TransacaoEntity transacao
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
    public ProdutoEntity getProdutoEntity() {
        return this.produto;
    }

    @Override
    public TransacaoEntity getTransacaoEntity() {
        return this.transacao;
    }

    @Override
    public BigDecimal calcularTotal() {
        return this.quantidade.multiply(this.valorUnitario);
    }
}
