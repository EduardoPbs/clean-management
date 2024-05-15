package br.com.lgmanagement.lgManagement.application.usecases.movimentacao;

import br.com.lgmanagement.lgManagement.application.gateways.movimentacao.MovimentacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

import java.util.List;

public class ShowAllMovementsInteractor {

    private final MovimentacaoGateway movimentacaoGateway;

    public ShowAllMovementsInteractor(MovimentacaoGateway movimentacaoGateway) {
        this.movimentacaoGateway = movimentacaoGateway;
    }

    public List<Movimentacao> showAllMovements() {
        return movimentacaoGateway.showAllMovements();
    }
}
