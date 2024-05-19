package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class ShowTransacoesByDateInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowTransacoesByDateInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> showTransacoesByDate(int month, int day) {
        return transacaoGateway.findTransactionsByDate(month, day);
    }
}
