package br.com.lgmanagement.lgManagement.application.usecases.caixa;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;

import java.util.List;

public class ShowAllCaixasInteractor {

    private final CaixaGateway caixaGateway;

    public ShowAllCaixasInteractor(CaixaGateway caixaGateway) {
        this.caixaGateway = caixaGateway;
    }

    public List<Caixa> showAllCaixas() {
        return caixaGateway.showAllCaixas();
    }
}
