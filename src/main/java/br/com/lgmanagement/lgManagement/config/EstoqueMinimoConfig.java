package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.infra.gateways.EstoqueMinimoRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.gateways.ProdutoRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.estoque_minimo.EstoqueMinimoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.estoque_minimo.EstoqueMinimoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueMinimoConfig {

    @Bean
    EstoqueMinimoRepositoryGateway estoqueMinimoRepositoryGateway(
            EstoqueMinimoRepository estoqueMinimoRepository,
            ProdutoRepositoryGateway produtoRepositoryGateway,
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        return new EstoqueMinimoRepositoryGateway(
                estoqueMinimoRepository,
                produtoRepositoryGateway,
                produtoRepository,
                produtoEntityMapper
        );
    }

    @Bean
    EstoqueMinimoEntityMapper estoqueMinimoEntityMapper(ProdutoEntityMapper produtoEntityMapper) {
        return new EstoqueMinimoEntityMapper(produtoEntityMapper);
    }
}
