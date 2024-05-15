package br.com.lgmanagement.lgManagement.application.usecases.caixa;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;

import java.util.List;

public class FindCaixaByDateInteractor {

    private final CaixaGateway caixaGateway;

    public FindCaixaByDateInteractor(CaixaGateway caixaGateway) {
        this.caixaGateway = caixaGateway;
    }

    public List<Caixa> findCaixaByDate(int month, int day) {
        return caixaGateway.findCaixaByDate(month, day);
    }
}
