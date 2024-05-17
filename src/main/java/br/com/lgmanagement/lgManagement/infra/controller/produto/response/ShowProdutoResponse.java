package br.com.lgmanagement.lgManagement.infra.controller.produto.response;

import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;

import java.math.BigDecimal;
import java.util.List;

public record ShowProdutoResponse(
        String id,
        String nome,
        String codigo,
        BigDecimal valor,
        BigDecimal valorOriginal,
        List<Categoria> categorias,
        BigDecimal estoque,
        Boolean ativo
) {
    public ShowProdutoResponse(ProdutoEntity produtoEntity) {
        this(
                produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getCodigo(),
                produtoEntity.getValor(),
                produtoEntity.getValorOriginal(),
                produtoEntity.getCategorias(),
                produtoEntity.getEstoque(),
                produtoEntity.getAtivo()
        );
    }
}
