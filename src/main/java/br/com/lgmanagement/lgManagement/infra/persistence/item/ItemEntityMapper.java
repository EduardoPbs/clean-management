package br.com.lgmanagement.lgManagement.infra.persistence.item;

import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ItemEntityMapper {

    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;
    private final TransacaoRepository transacaoRepository;
    private final TransacaoEntityMapper transacaoEntityMapper;

    public ItemEntityMapper(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper,
            TransacaoRepository transacaoRepository,
            TransacaoEntityMapper transacaoEntityMapper
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
        this.transacaoRepository = transacaoRepository;
        this.transacaoEntityMapper = transacaoEntityMapper;
    }

    public Item toDomain(ItemEntity itemEntity) {
        ProdutoEntity produtoEntity = produtoRepository
                .findById(itemEntity.getProdutoEntity().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

        TransacaoEntity transacaoEntity = transacaoRepository
                .findById(itemEntity.getTransacaoEntity().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transação não encontrada."));
        return new Item(
                itemEntity.getId(),
                itemEntity.getValorUnitario(),
                itemEntity.getQuantidade(),
                produtoEntityMapper.toDomain(produtoEntity),
                transacaoEntityMapper.toDomain(transacaoEntity)
        );
    }

    public ItemEntity toEntity(Item item) {
        return new ItemEntity(
                item.getId(),
                item.getValorUnitario(),
                item.getQuantidade(),
                produtoEntityMapper.toEntity(item.getProduto()),
                transacaoEntityMapper.toEntity(item.getTransacao())
        );
    }
}
