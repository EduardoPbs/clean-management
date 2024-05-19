package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class ShowTransacoesByMonthInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowTransacoesByMonthInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> showTransactionsByMonth(int month) {
        return transacaoGateway.findTransactionsByMonth(month);
    }
}
