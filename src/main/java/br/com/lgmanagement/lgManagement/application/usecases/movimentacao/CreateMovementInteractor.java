package br.com.lgmanagement.lgManagement.application.usecases.movimentacao;

import br.com.lgmanagement.lgManagement.application.gateways.movimentacao.MovimentacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

import java.math.BigDecimal;

public class CreateMovementInteractor {

    private final MovimentacaoGateway movimentacaoGateway;

    public CreateMovementInteractor(MovimentacaoGateway movimentacaoGateway) {
        this.movimentacaoGateway = movimentacaoGateway;
    }

    public Movimentacao registerMovement(BigDecimal valor, Caixa caixa, TransacaoType transacaoType) {
        return movimentacaoGateway.registerMovement(valor, caixa, transacaoType);
    }
}
