package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;

public class DeleteProdutoInteractor {

    private final ProdutoGateway produtoGateway;

    public DeleteProdutoInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void deleteProduto(String id) {
        produtoGateway.deleteProduto(id);
    }
}
