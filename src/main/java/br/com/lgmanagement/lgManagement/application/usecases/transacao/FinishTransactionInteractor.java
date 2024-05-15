package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;

public class FinishTransactionInteractor {

    private final TransacaoGateway transacaoGateway;

    public FinishTransactionInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public void finalizeTransaction(String id) {
        transacaoGateway.finalizeTransaction(id);
    }
}
