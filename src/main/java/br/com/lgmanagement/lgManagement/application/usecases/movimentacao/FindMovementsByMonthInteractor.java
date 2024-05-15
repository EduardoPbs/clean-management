package br.com.lgmanagement.lgManagement.application.usecases.movimentacao;

import br.com.lgmanagement.lgManagement.application.gateways.movimentacao.MovimentacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

import java.util.List;

public class FindMovementsByMonthInteractor {

    private final MovimentacaoGateway movimentacaoGateway;

    public FindMovementsByMonthInteractor(MovimentacaoGateway movimentacaoGateway) {
        this.movimentacaoGateway = movimentacaoGateway;
    }

    public List<Movimentacao> findMovementsByMonth(int month) {
        return movimentacaoGateway.findMovementsByMonth(month);
    }
}
