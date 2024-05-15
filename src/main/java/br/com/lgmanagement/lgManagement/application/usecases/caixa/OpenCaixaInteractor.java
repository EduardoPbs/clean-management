package br.com.lgmanagement.lgManagement.application.usecases.caixa;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;

import java.math.BigDecimal;

public class OpenCaixaInteractor {

    private final CaixaGateway caixaGateway;

    public OpenCaixaInteractor(CaixaGateway caixaGateway) {
        this.caixaGateway = caixaGateway;
    }

    public Boolean openCaixa(BigDecimal valorAbertura) {
        return caixaGateway.openCaixa(valorAbertura);
    }
}
