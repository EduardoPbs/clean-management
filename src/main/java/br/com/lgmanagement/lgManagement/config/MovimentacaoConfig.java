package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.application.gateways.movimentacao.MovimentacaoGateway;
import br.com.lgmanagement.lgManagement.application.usecases.movimentacao.*;
import br.com.lgmanagement.lgManagement.infra.gateways.MovimentacaoRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovimentacaoConfig {

    @Bean
    CreateMovementInteractor createMovementInteractor(MovimentacaoGateway movimentacaoGateway) {
        return new CreateMovementInteractor(movimentacaoGateway);
    }

    @Bean
    ShowAllMovementsInteractor showAllMovementsInteractor(MovimentacaoGateway movimentacaoGateway) {
        return new ShowAllMovementsInteractor(movimentacaoGateway);
    }

    @Bean
    FindMovementsByMonthInteractor findMovementsByMonthInteractor(MovimentacaoGateway movimentacaoGateway) {
        return new FindMovementsByMonthInteractor(movimentacaoGateway);
    }

    @Bean
    FindMovementsByDateInteractor findMovementsByDateInteractor (MovimentacaoGateway movimentacaoGateway) {
        return new FindMovementsByDateInteractor(movimentacaoGateway);
    }

    @Bean
    MovimentacaoRepositoryGateway movimentacaoRepositoryGateway(
            MovimentacaoRepository movimentacaoRepository,
            MovimentacaoEntityMapper movimentacaoEntityMapper,
            CaixaRepository caixaRepository,
            CaixaEntityMapper caixaEntityMapper
    ) {
        return new MovimentacaoRepositoryGateway(
                movimentacaoRepository,
                movimentacaoEntityMapper,
                caixaRepository,
                caixaEntityMapper
        );
    }

    @Bean
    MovimentacaoEntityMapper movimentacaoEntityMapper() {
        return new MovimentacaoEntityMapper();
    }
}
