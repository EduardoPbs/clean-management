package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.gateways.estoque_minimo.EstoqueMinimoGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.estoque_minimo.EstoqueMinimoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.estoque_minimo.EstoqueMinimoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class EstoqueMinimoRepositoryGateway implements EstoqueMinimoGateway {

    private final EstoqueMinimoRepository estoqueMinimoRepository;
    private final ProdutoRepositoryGateway produtoRepositoryGateway;
    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    public EstoqueMinimoRepositoryGateway(
            EstoqueMinimoRepository estoqueMinimoRepository,
            ProdutoRepositoryGateway produtoRepositoryGateway,
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        this.estoqueMinimoRepository = estoqueMinimoRepository;
        this.produtoRepositoryGateway = produtoRepositoryGateway;
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    /**
     * Quando o <b>id</b> do produto informado não está presente e o mesmo possui estoque menor ou igual a <b>10</b>
     * um novo registro é cadastrado. <br>
     * Quando o <b>id</b> do produto está presente o valor do campo <code>estoque_minimo</code> é atualizado.<br>
     * Quando o <b>id</b> do produto está presente porém seu estoque for maior que <b>10</b> o registro será excluído. <br>
     * Por padrão retorna <code>false</code> caso nenhum das condições sejam atendidas.
     * @param produtoId Id do produto para verificar registro no banco de dados.
     */
    @Override
    public Boolean verifyEstoqueMinimo(String produtoId) {
        ProdutoEntity produtoEntity = produtoEntityMapper.toEntity(produtoRepositoryGateway.showProduto(produtoId));
        Optional<EstoqueMinimoEntity> estoqueMinimoEntity = estoqueMinimoRepository
                .findByProdutoEntityId(produtoEntity.getId());

        Boolean isEstoqueMinimo = produtoEntity.getEstoque().compareTo(new BigDecimal("10")) <= 0;

        if (!estoqueMinimoEntity.isPresent() && isEstoqueMinimo) {
            criarRegistro(produtoEntity);
        }

        if (estoqueMinimoEntity.isPresent() && isEstoqueMinimo) {
            atualizarRegistro(produtoEntity, estoqueMinimoEntity.get());
        }

        if (estoqueMinimoEntity.isPresent() && !isEstoqueMinimo) {
            excluirRegistro(produtoEntity, estoqueMinimoEntity.get());
        }

        return Boolean.FALSE;
    }

    private Boolean criarRegistro(ProdutoEntity produtoEntity) {
        EstoqueMinimoEntity newEstoqueMinimoEntity = new EstoqueMinimoEntity(produtoEntity, produtoEntity.getEstoque());
        produtoEntity.setEstoqueMinimoEntity(newEstoqueMinimoEntity);
        produtoRepository.save(produtoEntity);
        estoqueMinimoRepository.save(newEstoqueMinimoEntity);
        return Boolean.TRUE;
    }

    private Boolean atualizarRegistro(ProdutoEntity produtoEntity, EstoqueMinimoEntity estoqueMinimoEntity) {
        estoqueMinimoEntity.setEstoque_minimo(produtoEntity.getEstoque());
        estoqueMinimoRepository.save(estoqueMinimoEntity);
        return Boolean.TRUE;
    }

    private Boolean excluirRegistro(ProdutoEntity produtoEntity, EstoqueMinimoEntity estoqueMinimoEntity) {
        produtoEntity.setEstoqueMinimoEntity(null);
        estoqueMinimoEntity.setProdutoEntity(null);
        produtoRepository.save(produtoEntity);
        estoqueMinimoRepository.delete(estoqueMinimoEntity);
        return Boolean.TRUE;
    }
}
