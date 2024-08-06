package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.PagamentoType;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class CreateTransacaoInteractor {

    private final TransacaoGateway transacaoGateway;

    public CreateTransacaoInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public Transacao registrarTransacao(List<Item> itens, String funcionarioId, PagamentoType pagamentoType, TransacaoType transacaoType) {
        return transacaoGateway.registrarTransacao(itens, funcionarioId, pagamentoType, transacaoType);
    }
}
