package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;
import br.com.lgmanagement.lgManagement.application.usecases.caixa.*;
import br.com.lgmanagement.lgManagement.infra.gateways.CaixaRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaixaConfig {

    @Bean
    ShowCaixaInteractor showCaixaInteractor(CaixaGateway caixaGateway) {
        return new ShowCaixaInteractor(caixaGateway);
    }

    @Bean
    OpenCaixaInteractor openCaixaInteractor(CaixaGateway caixaGateway) {
        return new OpenCaixaInteractor(caixaGateway);
    }

    @Bean
    CloseCaixaInteractor closeCaixaInteractor(CaixaGateway caixaGateway) {
        return new CloseCaixaInteractor(caixaGateway);
    }

    @Bean
    ShowAllCaixasInteractor showAllCaixasInteractor(CaixaGateway caixaGateway) {
        return new ShowAllCaixasInteractor(caixaGateway);
    }

    @Bean
    FindCaixaByDateInteractor findCaixaByDateInteractor(CaixaGateway caixaGateway) {
        return new FindCaixaByDateInteractor(caixaGateway);
    }

    @Bean
    FindCaixaByMonthInteractor findCaixaByMonthInteractor(CaixaGateway caixaGateway) {
        return new FindCaixaByMonthInteractor(caixaGateway);
    }

    @Bean
    CaixaRepositoryGateway caixaRepositoryGateway(
            CaixaRepository caixaRepository,
            CaixaEntityMapper caixaEntityMapper
    ) {
        return new CaixaRepositoryGateway(
                caixaRepository,
                caixaEntityMapper
        );
    }

    @Bean
    CaixaEntityMapper caixaEntityMapper(MovimentacaoEntityMapper movimentacaoEntityMapper) {
        return new CaixaEntityMapper(movimentacaoEntityMapper);
    }
}
