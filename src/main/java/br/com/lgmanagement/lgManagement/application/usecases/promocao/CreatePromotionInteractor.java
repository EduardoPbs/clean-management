package br.com.lgmanagement.lgManagement.application.usecases.promocao;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreatePromotionInteractor {

    private final PromocaoGateway promocaoGateway;

    public CreatePromotionInteractor(PromocaoGateway promocaoGateway) {
        this.promocaoGateway = promocaoGateway;
    }

    public Promocao createPromotion(
            String produtoId,
            BigDecimal porventagemDesconto,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        return promocaoGateway.createPromotion(produtoId, porventagemDesconto, dataInicio, dataFim);
    }
}
