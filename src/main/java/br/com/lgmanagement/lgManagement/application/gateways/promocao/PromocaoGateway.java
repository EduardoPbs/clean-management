package br.com.lgmanagement.lgManagement.application.gateways.promocao;

import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PromocaoGateway {

    Promocao createPromotion(
            String produtoId,
            BigDecimal valorPorcentagem,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    );

    List<Promocao> showAllPromotions();

    List<Promocao> showPromotionsByProduct(String produtoId);

    Boolean activePromotion(String id);

    Boolean disablePromotion(String id);

    Promocao showPromotionById(String id);
}
