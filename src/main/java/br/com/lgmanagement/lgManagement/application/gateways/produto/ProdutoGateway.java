package br.com.lgmanagement.lgManagement.application.gateways.produto;

import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

import java.util.List;

public interface ProdutoGateway {
    Produto createProduto(Produto produto);

    List<Produto> showAllProdutos();

    Produto updateProduto(String id, Produto produto);

    void deleteProduto(String id);

    Produto showProduto(String id);

    void activeProduto(String id);

    List<Produto> showProdutosWithLowStock();

    List<Produto> showAllProdutosAtivos();
}
