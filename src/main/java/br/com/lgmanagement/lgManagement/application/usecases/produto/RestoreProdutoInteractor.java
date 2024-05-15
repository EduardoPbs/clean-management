package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;

public class RestoreProdutoInteractor {

    private final ProdutoGateway produtoGateway;


    public RestoreProdutoInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void activeProduto(String id) {
        produtoGateway.activeProduto(id);
    }
}
