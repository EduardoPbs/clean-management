package br.com.lgmanagement.lgManagement.application.usecases.movimentacao;

import br.com.lgmanagement.lgManagement.application.gateways.movimentacao.MovimentacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

import java.util.List;

public class FindMovementsByDateInteractor {

    private final MovimentacaoGateway movimentacaoGateway;

    public FindMovementsByDateInteractor(MovimentacaoGateway movimentacaoGateway) {
        this.movimentacaoGateway = movimentacaoGateway;
    }

    public List<Movimentacao> findMovementsByDate(int month, int day) {
        return movimentacaoGateway.findMovementsByDate(month, day);
    }
}
