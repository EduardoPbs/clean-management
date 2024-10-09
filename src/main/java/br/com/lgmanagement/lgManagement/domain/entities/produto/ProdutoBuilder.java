package br.com.lgmanagement.lgManagement.domain.entities.produto;

import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoBuilder {

    private Produto produto;

    public Produto withNomeAndCodigo(String nome, String codigo) {
        this.produto = new Produto(nome, codigo, BigDecimal.ZERO, BigDecimal.ZERO, new ArrayList<>(), BigDecimal.ZERO, false, BigDecimal.ZERO);
        return this.produto;
    }

    public void includeValor(BigDecimal valor) {
        this.produto.setValor(valor);
    }

    public void includeCategorias(List<Categoria> categorias) {
        this.produto.setCategorias(categorias);
    }

    public void includeEstoque(BigDecimal estoque) {
        this.produto.setEstoque(estoque);
    }

    public void includePromocao(Promocao promocao) {
        this.produto.setPromocao(promocao);
    }

    public void includeValorCompra(BigDecimal valorCompra) {
        this.produto.setValorCompra(valorCompra);
    }
}
