package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.application.usecases.produto.*;
import br.com.lgmanagement.lgManagement.infra.gateways.ProdutoRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    @Bean
    CreateProdutoInteractor createMercadoriaInteractor(ProdutoGateway produtoGateway) {
        return new CreateProdutoInteractor(produtoGateway);
    }

    @Bean
    ShowAllProdutosInteractor showAllMercadoriasInteractor(ProdutoGateway produtoGateway) {
        return new ShowAllProdutosInteractor(produtoGateway);
    }

    @Bean
    UpdateProdutoInteractor updateMercadoriaInteractor(ProdutoGateway produtoGateway) {
        return new UpdateProdutoInteractor(produtoGateway);
    }

    @Bean
    DeleteProdutoInteractor deleteMercadoriaInteractor(ProdutoGateway produtoGateway) {
        return new DeleteProdutoInteractor(produtoGateway);
    }

    @Bean
    ShowOneProdutoInteractor showOneMercadoriaInteractor(ProdutoGateway produtoGateway) {
        return new ShowOneProdutoInteractor(produtoGateway);
    }

    @Bean
    RestoreProdutoInteractor restoreProdutoInteractor(ProdutoGateway produtoGateway) {
        return new RestoreProdutoInteractor(produtoGateway);
    }

    @Bean
    ShowProdutoWithLowStockInteractor showProdutoWithLowStock(ProdutoGateway produtoGateway) {
        return new ShowProdutoWithLowStockInteractor(produtoGateway);
    }

    @Bean
    ShowAllProdutosAtivosInteractor showAllProdutosAtivosInteractor(ProdutoGateway produtoGateway) {
        return new ShowAllProdutosAtivosInteractor(produtoGateway);
    }

    @Bean
    ProdutoRepositoryGateway mercadoriaRepositoryGateway(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        return new ProdutoRepositoryGateway(
                produtoRepository,
                produtoEntityMapper
        );
    }

    @Bean
    ProdutoEntityMapper mercadoriaEntityMapper(ProdutoRepository produtoRepository) {
        return new ProdutoEntityMapper(produtoRepository);
    }


}
