package br.com.lgmanagement.lgManagement.application.gateways.caixa;

import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;

import java.math.BigDecimal;
import java.util.List;

public interface CaixaGateway {

    Caixa showCaixa();

    Boolean openCaixa(BigDecimal valorAbertura);

    Boolean closeCaixa();

    List<Caixa> showAllCaixas();

    List<Caixa> findCaixaByMonth(int month);

    List<Caixa> findCaixaByDate(int month, int day);
}
