package br.com.lgmanagement.lgManagement.application.usecases.caixa;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;

public class CloseCaixaInteractor {

    private final CaixaGateway caixaGateway;

    public CloseCaixaInteractor(CaixaGateway caixaGateway) {
        this.caixaGateway = caixaGateway;
    }

    public Boolean closeCaixa() {
        return caixaGateway.closeCaixa();
    }
}
