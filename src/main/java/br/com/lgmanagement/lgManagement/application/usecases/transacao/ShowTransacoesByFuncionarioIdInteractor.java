package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class ShowTransacoesByFuncionarioIdInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowTransacoesByFuncionarioIdInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> mostrarTransacoesPorFuncionarioId(String id) {
        return transacaoGateway.mostrarTransacoesPorFuncionarioId(id);
    }
}
