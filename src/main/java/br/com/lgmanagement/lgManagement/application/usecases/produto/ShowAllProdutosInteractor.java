package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

import java.util.List;

public class ShowAllProdutosInteractor {

    private final ProdutoGateway produtoGateway;

    public ShowAllProdutosInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<Produto> showAllProdutos() {
        return produtoGateway.showAllProdutos();
    }
}
