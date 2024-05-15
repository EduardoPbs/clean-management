package br.com.lgmanagement.lgManagement.application.usecases.produto;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

import java.util.List;

public class ShowProdutoWithLowStockInteractor {

    private final ProdutoGateway produtoGateway;

    public ShowProdutoWithLowStockInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public List<Produto> showProdutosWithLowStock() {
        return produtoGateway.showProdutosWithLowStock();
    }
}
