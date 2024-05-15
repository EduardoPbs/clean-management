package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

public class UpdateProdutoInteractor {

    private final ProdutoGateway produtoGateway;

    public UpdateProdutoInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto updateProduto(String id, Produto produto) {
        return produtoGateway.updateProduto(id, produto);
    }
}
