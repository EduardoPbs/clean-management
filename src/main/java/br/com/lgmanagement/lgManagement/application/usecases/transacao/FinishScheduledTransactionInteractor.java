package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;

public class FinishScheduledTransactionInteractor {

    private final TransacaoGateway transacaoGateway;

    public FinishScheduledTransactionInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public void finalizeTransaction() {
        transacaoGateway.finalizeScheduledTransacations();
    }
}
