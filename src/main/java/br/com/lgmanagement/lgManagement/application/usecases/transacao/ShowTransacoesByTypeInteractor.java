package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class ShowTransacoesByTypeInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowTransacoesByTypeInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> mostrarTransacoesPorTipo(TransacaoType transacaoType) {
        return transacaoGateway.mostrarTransacoesPorTipo(transacaoType);
    }

}
