package br.com.lgmanagement.lgManagement.application.usecases.promocao;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;

import java.util.List;

public class ShowAllPromotionsInteractor {

    private final PromocaoGateway promocaoGateway;

    public ShowAllPromotionsInteractor(PromocaoGateway promocaoGateway) {
        this.promocaoGateway = promocaoGateway;
    }

    public List<Promocao> showAllPromotions() {
        return promocaoGateway.showAllPromotions();
    }
}
