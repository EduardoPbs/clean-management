package br.com.lgmanagement.lgManagement.infra.persistence.produto;

import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;
import br.com.lgmanagement.lgManagement.infra.controller.produto.request.CreateProdutoRequest;

import java.util.Optional;

public class ProdutoEntityMapper {

    private final ProdutoRepository produtoRepository;

    public ProdutoEntityMapper(
            ProdutoRepository produtoRepository
    ) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoEntity toEntity(Produto produto) {
        Optional<ProdutoEntity> existsProduto = produtoRepository
                .findByCodigo(produto.getCodigo());

        if (existsProduto.isEmpty()) {
            return new ProdutoEntity(
                    produto.getNome(),
                    produto.getCodigo(),
                    produto.getValor(),
                    produto.getCategorias(),
                    produto.getEstoque(),
                    produto.getAtivo(),
                    produto.getValorCompra()
            );
        }

        return new ProdutoEntity(
                existsProduto.get().getId(),
                produto.getNome(),
                produto.getCodigo(),
                produto.getValor(),
                produto.getCategorias(),
                produto.getEstoque(),
                produto.getAtivo(),
                produto.getValorOriginal(),
                produto.getValorCompra()
        );
    }

    public ProdutoEntity toEntity(CreateProdutoRequest mercadoriaRequest) {
        Optional<ProdutoEntity> existsMercadoria = produtoRepository
                .findByCodigo(mercadoriaRequest.codigo());

        if (existsMercadoria.isEmpty()) {
            return new ProdutoEntity(
                    mercadoriaRequest.nome(),
                    mercadoriaRequest.codigo(),
                    mercadoriaRequest.valor(),
                    mercadoriaRequest.categorias(),
                    mercadoriaRequest.estoque(),
                    mercadoriaRequest.ativo(),
                    mercadoriaRequest.valorCompra()
            );
        }

        return new ProdutoEntity(
                existsMercadoria.get().getId(),
                mercadoriaRequest.nome(),
                mercadoriaRequest.codigo(),
                mercadoriaRequest.valor(),
                mercadoriaRequest.categorias(),
                mercadoriaRequest.estoque(),
                existsMercadoria.get().getAtivo(),
                existsMercadoria.get().getValorOriginal(),
                existsMercadoria.get().getValorCompra()
        );
    }

    public Produto toDomain(ProdutoEntity produtoEntity) {
        return new Produto(
                produtoEntity.getNome(),
                produtoEntity.getCodigo(),
                produtoEntity.getValor(),
                produtoEntity.getValorOriginal(),
                produtoEntity.getCategorias(),
                produtoEntity.getEstoque(),
                produtoEntity.getAtivo(),
                produtoEntity.getValorCompra()
        );
    }

    public Produto toDomain(CreateProdutoRequest createProdutoRequest) {
        return new Produto(
                createProdutoRequest.nome(),
                createProdutoRequest.codigo(),
                createProdutoRequest.valor(),
                createProdutoRequest.valor(),
                createProdutoRequest.categorias(),
                createProdutoRequest.estoque(),
                createProdutoRequest.ativo(),
                createProdutoRequest.valorCompra()
        );
    }
}
