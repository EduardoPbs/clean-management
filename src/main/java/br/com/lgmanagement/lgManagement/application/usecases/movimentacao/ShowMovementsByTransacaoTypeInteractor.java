package br.com.lgmanagement.lgManagement.application.usecases.movimentacao;

import br.com.lgmanagement.lgManagement.application.gateways.movimentacao.MovimentacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

import java.util.List;

public class ShowMovementsByTransacaoTypeInteractor {

    private final MovimentacaoGateway movimentacaoGateway;

    public ShowMovementsByTransacaoTypeInteractor(MovimentacaoGateway movimentacaoGateway) {
        this.movimentacaoGateway = movimentacaoGateway;
    }

    public List<Movimentacao> showMovementsByType(TransacaoType transacaoType) {
        return movimentacaoGateway.findMovementsByTransacaoType(transacaoType);
    };
}
