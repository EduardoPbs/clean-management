package br.com.lgmanagement.lgManagement.application.usecases.caixa;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;

import java.util.List;

public class FindCaixaByMonthInteractor {

    private final CaixaGateway caixaGateway;

    public FindCaixaByMonthInteractor(CaixaGateway caixaGateway) {
        this.caixaGateway = caixaGateway;
    }

    public List<Caixa> findCaixaByMonth(int month) {
        return caixaGateway.findCaixaByMonth(month);
    }
}
