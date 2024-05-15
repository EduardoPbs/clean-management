package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class ShowAllTransacoesInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowAllTransacoesInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Transacao> mostrarTransacoes() {
        return transacaoGateway.listarTransacoes();
    }
}
