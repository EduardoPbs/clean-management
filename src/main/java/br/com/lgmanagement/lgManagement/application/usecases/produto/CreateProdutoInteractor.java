package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

public class CreateProdutoInteractor {

    private final ProdutoGateway produtoGateway;

    public CreateProdutoInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto createProduto(Produto produto) {
        return produtoGateway.createProduto(produto);
    }
}
