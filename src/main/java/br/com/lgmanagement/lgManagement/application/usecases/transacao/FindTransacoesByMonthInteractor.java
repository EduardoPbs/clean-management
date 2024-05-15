package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class FindTransacoesByMonthInteractor {

    private final TransacaoGateway transacaoGateway;

    public FindTransacoesByMonthInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> findTransactionsByMonth(int month) {
        return transacaoGateway.findTransactionsByMonth(month);
    }
}
