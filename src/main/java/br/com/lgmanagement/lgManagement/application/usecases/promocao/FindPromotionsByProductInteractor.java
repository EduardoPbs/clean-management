package br.com.lgmanagement.lgManagement.application.usecases.promocao;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;

import java.util.List;

public class FindPromotionsByProductInteractor {

    private final PromocaoGateway promocaoGateway;

    public FindPromotionsByProductInteractor(PromocaoGateway promocaoGateway) {
        this.promocaoGateway = promocaoGateway;
    }

    public List<Promocao> showPromotionsByProduct(String produtoId) {
        return promocaoGateway.showPromotionsByProduct(produtoId);
    }
}
