package br.com.lgmanagement.lgManagement.infra.controller.transacao.response;

import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ShowItemResponse(

        @JsonProperty("produto_id")
        String produtoId,

        @JsonProperty("produto_nome")
        String produtoNome,

        BigDecimal quantidade,

        @JsonProperty("valor_unitario")
        BigDecimal valorUnitario
) {
    public ShowItemResponse(BigDecimal quantidade, ProdutoEntity produtoEntity, BigDecimal valorUnitario) {
        this(produtoEntity.getId(), produtoEntity.getNome(), quantidade, valorUnitario);
    }
}
