package br.com.lgmanagement.lgManagement.infra.persistence.promocao;

import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PromocaoEntityMapper {

    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    public PromocaoEntityMapper(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    public PromocaoEntity toEntity(Promocao promocao) {
        ProdutoEntity produtoEntity = produtoRepository
                .findByCodigo(promocao.getProduto().getCodigo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

        return new PromocaoEntity(
                promocao.getId(),
                produtoEntity,
                promocao.getAtivo(),
                promocao.getPorcentagemDesconto(),
                promocao.getDataInicio(),
                promocao.getDataFim()
        );
    }

    public Promocao toDomain(PromocaoEntity promocaoEntity) {
        return new Promocao(
                promocaoEntity.getId(),
                produtoEntityMapper.toDomain(promocaoEntity.getProdutoEntity()),
                promocaoEntity.getAtivo(),
                promocaoEntity.getPorcentagemDesconto(),
                promocaoEntity.getDataInicio(),
                promocaoEntity.getDataFim()
        );
    }
}
