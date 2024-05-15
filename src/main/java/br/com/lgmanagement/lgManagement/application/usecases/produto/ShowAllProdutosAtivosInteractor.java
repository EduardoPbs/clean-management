package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

import java.util.List;

public class ShowAllProdutosAtivosInteractor {

    private final ProdutoGateway produtoGateway;

    public ShowAllProdutosAtivosInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<Produto> showAllProdutosAtivos() {
        return produtoGateway.showAllProdutosAtivos();
    }
}
