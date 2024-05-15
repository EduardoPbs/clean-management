package br.com.lgmanagement.lgManagement.domain.entities.produto;

import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoBuilder {

    private Produto produto;

    public Produto withNomeAndCodigo(String nome, String codigo) {
        this.produto = new Produto(nome, codigo, BigDecimal.ZERO, new ArrayList<>(), BigDecimal.ZERO, false);
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
}
