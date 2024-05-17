package br.com.lgmanagement.lgManagement.application.gateways.movimentacao;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

import java.math.BigDecimal;
import java.util.List;

public interface MovimentacaoGateway {

    Movimentacao registerMovement(BigDecimal valor, Caixa caixa, TransacaoType transacaoType);

    List<Movimentacao> showAllMovements();

    List<Movimentacao> findMovementsByMonth(int month);

    List<Movimentacao> findMovementsByDate(int month, int day);
}
