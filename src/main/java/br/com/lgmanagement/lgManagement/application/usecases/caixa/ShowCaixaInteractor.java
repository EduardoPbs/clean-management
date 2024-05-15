package br.com.lgmanagement.lgManagement.application.usecases.caixa;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;

public class ShowCaixaInteractor {

    private final CaixaGateway caixaGateway;

    public ShowCaixaInteractor(CaixaGateway caixaGateway) {
        this.caixaGateway = caixaGateway;
    }

    public Caixa showCaixa() {
        return caixaGateway.showCaixa();
    }
}
