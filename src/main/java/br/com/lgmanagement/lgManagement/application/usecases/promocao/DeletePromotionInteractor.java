package br.com.lgmanagement.lgManagement.application.usecases.promocao;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;

public class DeletePromotionInteractor {

    private final PromocaoGateway promocaoGateway;

    public DeletePromotionInteractor(PromocaoGateway promocaoGateway) {
        this.promocaoGateway = promocaoGateway;
    }

    public Boolean deletePromotion(String id) {
        return promocaoGateway.deletePromotion(id);
    }
}
