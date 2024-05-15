package br.com.lgmanagement.lgManagement.application.usecases.promocao;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;

public class ActivePromotionInteractor {

    private final PromocaoGateway promocaoGateway;

    public ActivePromotionInteractor(PromocaoGateway promocaoGateway) {
        this.promocaoGateway = promocaoGateway;
    }

    public Boolean activePromotion(String id) {
        return promocaoGateway.activePromotion(id);
    }
}
