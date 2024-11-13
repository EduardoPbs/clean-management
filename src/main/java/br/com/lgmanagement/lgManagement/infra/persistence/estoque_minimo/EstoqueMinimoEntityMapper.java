package br.com.lgmanagement.lgManagement.infra.persistence.estoque_minimo;

import br.com.lgmanagement.lgManagement.domain.entities.estoque_minimo.EstoqueMinimo;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;

public class EstoqueMinimoEntityMapper {

    private final ProdutoEntityMapper produtoEntityMapper;

    public EstoqueMinimoEntityMapper(ProdutoEntityMapper produtoEntityMapper) {
        this.produtoEntityMapper = produtoEntityMapper;
    }

    public EstoqueMinimoEntity toEntity(EstoqueMinimo estoqueMinimo) {
        return new EstoqueMinimoEntity(
                estoqueMinimo.getId(),
                produtoEntityMapper.toEntity(estoqueMinimo.getProduto()),
                estoqueMinimo.getEstoque_minimo()
        );
    }

    public EstoqueMinimo toDomain(EstoqueMinimoEntity estoqueMinimoEntity) {
        return new EstoqueMinimo(
                estoqueMinimoEntity.getId(),
                produtoEntityMapper.toDomain(estoqueMinimoEntity.getProdutoEntity()),
                estoqueMinimoEntity.getEstoque_minimo()
        );
    }
}
