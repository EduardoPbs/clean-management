package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class FindTransacoesByDateInteractor {

    private final TransacaoGateway transacaoGateway;

    public FindTransacoesByDateInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> findTransacoesByDate(int month, int day) {
        return transacaoGateway.findTransactionsByDate(month, day);
    }
}
