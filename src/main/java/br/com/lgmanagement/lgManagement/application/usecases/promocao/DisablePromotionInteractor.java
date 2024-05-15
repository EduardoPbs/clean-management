package br.com.lgmanagement.lgManagement.application.usecases.promocao;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;

public class DisablePromotionInteractor {

    private final PromocaoGateway promocaoGateway;

    public DisablePromotionInteractor(PromocaoGateway promocaoGateway) {
        this.promocaoGateway = promocaoGateway;
    }

    public Boolean disablePromotion(String id) {
        return promocaoGateway.disablePromotion(id);
    }
}
