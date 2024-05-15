package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

public class ShowOneProdutoInteractor {

    private final ProdutoGateway produtoGateway;

    public ShowOneProdutoInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto showProduto(String id) {
        return produtoGateway.showProduto(id);
    }
}
