package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class ShowTransacoesByStatusInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowTransacoesByStatusInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> showTransacoesByStatus(TransacaoStatus transacaoStatus) {
        return transacaoGateway.showTransacoesByStatus(transacaoStatus);
    }
}
