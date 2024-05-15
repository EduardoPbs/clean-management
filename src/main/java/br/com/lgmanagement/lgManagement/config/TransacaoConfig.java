package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.application.usecases.transacao.*;
import br.com.lgmanagement.lgManagement.infra.gateways.CaixaRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.gateways.MovimentacaoRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.gateways.TransacaoRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class TransacaoConfig {

    @Bean
    CreateTransacaoInteractor createTransacaoInteractor(TransacaoGateway transacaoGateway) {
        return new CreateTransacaoInteractor(transacaoGateway);
    }

    @Bean
    ShowAllTransacoesInteractor showAllTransacoesInteractor(TransacaoGateway transacaoGateway) {
        return new ShowAllTransacoesInteractor(transacaoGateway);
    }

    @Bean
    ShowTransacoesByFuncionarioIdInteractor showTransacoesByFuncionarioIdInteractor(TransacaoGateway transacaoGateway) {
        return new ShowTransacoesByFuncionarioIdInteractor(transacaoGateway);
    }

    @Bean
    ShowOneTransacaoInteractor showOneTransacaoInteractor(TransacaoGateway transacaoGateway) {
        return new ShowOneTransacaoInteractor(transacaoGateway);
    }

    @Bean
    ShowTransacoesByTypeInteractor showTransacoesByTypeInteractor(TransacaoGateway transacaoGateway) {
        return new ShowTransacoesByTypeInteractor(transacaoGateway);
    }

    @Bean
    ShowTransacoesByStatusInteractor showTransacoesByStatusInteractor(TransacaoGateway transacaoGateway) {
        return new ShowTransacoesByStatusInteractor(transacaoGateway);
    }

    @Bean
    CreateScheduledTransacaoInteractor createScheduledTransacaoInteractor(TransacaoGateway transacaoGateway) {
        return new CreateScheduledTransacaoInteractor(transacaoGateway);
    }

    @Bean
    FinishTransactionInteractor finishTransactionInteractor(TransacaoGateway transacaoGateway) {
        return new FinishTransactionInteractor(transacaoGateway);
    }

    @Bean
    FinishScheduledTransactionInteractor finishScheduledTransactionInteractor(TransacaoGateway transacaoGateway) {
        return new FinishScheduledTransactionInteractor(transacaoGateway);
    }

    @Bean
    ShowTransacaoItemsInteractor showTransacaoItemsInteractor(TransacaoGateway transacaoGateway) {
        return new ShowTransacaoItemsInteractor(transacaoGateway);
    }

    @Bean
    FindTransacoesByDateInteractor findTransacoesByDateInteractor(TransacaoGateway transacaoGateway) {
        return new FindTransacoesByDateInteractor(transacaoGateway);
    }

    @Bean
    FindTransacoesByMonthInteractor findTransacoesByMonthInteractor(TransacaoGateway transacaoGateway) {
        return new FindTransacoesByMonthInteractor(transacaoGateway);
    }

    @Bean
    TransacaoRepositoryGateway transacaoRepositoryGateway(
            TransacaoRepository transacaoRepository,
            TransacaoEntityMapper transacaoEntityMapper,
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper,
            FuncionarioRepository funcionarioRepository,
            ItemEntityMapper itemEntityMapper,
            ItemRepository itemRepository,
            MovimentacaoRepositoryGateway movimentacaoRepositoryGateway,
            CaixaRepositoryGateway caixaRepositoryGateway
    ) {
        return new TransacaoRepositoryGateway(
                transacaoRepository,
                transacaoEntityMapper,
                produtoRepository,
                produtoEntityMapper,
                funcionarioRepository,
                itemEntityMapper,
                itemRepository,
                movimentacaoRepositoryGateway,
                caixaRepositoryGateway
        );
    }

    @Bean
    TransacaoEntityMapper transacaoEntityMapper(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        return new TransacaoEntityMapper(
                produtoRepository,
                produtoEntityMapper
        );
    }
}
