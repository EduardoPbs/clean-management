package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class FinishScheduledTransactionInteractor {

    private final TransacaoGateway transacaoGateway;

    public FinishScheduledTransactionInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public void finalizeTransaction() {
        transacaoGateway.finalizeScheduledTransacations();
    }
}
