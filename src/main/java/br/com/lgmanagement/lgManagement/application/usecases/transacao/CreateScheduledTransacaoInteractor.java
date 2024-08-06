package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.PagamentoType;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class CreateScheduledTransacaoInteractor {

    private final TransacaoGateway transacaoGateway;

    public CreateScheduledTransacaoInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public Transacao scheduledTransaction(
            List<Item> itens,
            String funcionarioId,
            TransacaoType transacaoType,
            PagamentoType pagamentoType,
            String dateString
    ) {
        return transacaoGateway.registerScheduledTransacao(itens, funcionarioId, transacaoType, pagamentoType, dateString);
    }
}
