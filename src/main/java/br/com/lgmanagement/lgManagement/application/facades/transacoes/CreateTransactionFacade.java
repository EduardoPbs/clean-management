package br.com.lgmanagement.lgManagement.application.facades.transacoes;

import br.com.lgmanagement.lgManagement.application.usecases.transacao.CreateScheduledTransacaoInteractor;
import br.com.lgmanagement.lgManagement.application.usecases.transacao.CreateTransacaoInteractor;
import br.com.lgmanagement.lgManagement.domain.entities.PagamentoType;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class CreateTransactionFacade {

    private final CreateTransacaoInteractor createTransacaoInteractor;
    private final CreateScheduledTransacaoInteractor createScheduledTransacaoInteractor;

    public CreateTransactionFacade(
            CreateTransacaoInteractor createTransacaoInteractor,
            CreateScheduledTransacaoInteractor createScheduledTransacaoInteractor
    ) {
        this.createTransacaoInteractor = createTransacaoInteractor;
        this.createScheduledTransacaoInteractor = createScheduledTransacaoInteractor;
    }

    public Transacao create(List<Item> itens, String funcionarioId, PagamentoType pagamentoType, TransacaoType transacaoType) {
        return createTransacaoInteractor.registrarTransacao(itens, funcionarioId, pagamentoType, transacaoType);
    }

    public Transacao createScheduled(
            List<Item> itens,
            String funcionarioId,
            TransacaoType transacaoType,
            PagamentoType pagamentoType,
            String date
    ) {
        return createScheduledTransacaoInteractor.scheduledTransaction(
                itens,
                funcionarioId,
                transacaoType,
                pagamentoType,
                date
        );
    }
}
