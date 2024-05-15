package br.com.lgmanagement.lgManagement.application.usecases.transacao;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;

import java.util.List;

public class ShowTransacaoItemsInteractor {

    private final TransacaoGateway transacaoGateway;

    public ShowTransacaoItemsInteractor(TransacaoGateway transacaoGateway) {
        this.transacaoGateway = transacaoGateway;
    }

    public List<Item> showTransactionItems(String id) {
        return transacaoGateway.showTransactionItems(id);
    }
}
