package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

public class ShowOneTransacaoInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowOneTransacaoInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public Transacao mostrarTransacao(String id) {
        return transacaoGateway.mostrarTransacao(id);
    }
}
