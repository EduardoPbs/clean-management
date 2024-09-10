package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;
import br.com.lgmanagement.lgManagement.application.usecases.promocao.*;
import br.com.lgmanagement.lgManagement.infra.gateways.PromocaoRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.promocao.PromocaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.promocao.PromocaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class PromocaoConfig {

    @Bean
    CreatePromotionInteractor createPromotionInteractor(PromocaoGateway promocaoGateway) {
        return new CreatePromotionInteractor(promocaoGateway);
    }

    @Bean
    ShowAllPromotionsInteractor showAllPromotionsInteractor(PromocaoGateway promocaoGateway) {
        return new ShowAllPromotionsInteractor(promocaoGateway);
    }

    @Bean
    FindPromotionsByProductInteractor showPromotionsByProductInteractor(PromocaoGateway promocaoGateway) {
        return new FindPromotionsByProductInteractor(promocaoGateway);
    }

    @Bean
    ActivePromotionInteractor activePromotionInteractor(PromocaoGateway promocaoGateway) {
        return new ActivePromotionInteractor(promocaoGateway);
    }

    @Bean
    DisablePromotionInteractor disablePromotionInteractor(PromocaoGateway promocaoGateway) {
        return new DisablePromotionInteractor(promocaoGateway);
    }

    @Bean
    ShowPromotionByIdInteractor showPromotionByIdInteractor(PromocaoGateway promocaoGateway) {
        return new ShowPromotionByIdInteractor(promocaoGateway);
    }

    @Bean
    DeletePromotionInteractor deletePromotionInteractor(PromocaoGateway promocaoGateway) {
        return new DeletePromotionInteractor(promocaoGateway);
    }

    @Bean
    PromocaoEntityMapper promocaoEntityMapper(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        return new PromocaoEntityMapper(
                produtoRepository,
                produtoEntityMapper
        );
    }

    @Bean
    PromocaoRepositoryGateway promocaoRepositoryGateway(
            PromocaoEntityMapper promocaoEntityMapper,
            ProdutoRepository produtoRepository,
            PromocaoRepository promocaoRepository
    ) {
        return new PromocaoRepositoryGateway(
                promocaoEntityMapper,
                produtoRepository,
                promocaoRepository
        );
    }
}
