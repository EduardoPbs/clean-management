package br.com.lgmanagement.lgManagement.application.usecases.promocao;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;

public class ShowPromotionByIdInteractor {

    private final PromocaoGateway promocaoGateway;

    public ShowPromotionByIdInteractor(PromocaoGateway promocaoGateway) {
        this.promocaoGateway = promocaoGateway;
    }

    public Promocao showPromotionById(String id) {
        return promocaoGateway.showPromotionById(id);
    }
}
