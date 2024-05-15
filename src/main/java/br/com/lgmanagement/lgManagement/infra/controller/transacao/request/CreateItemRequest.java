package br.com.lgmanagement.lgManagement.infra.controller.transacao.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CreateItemRequest(

        @JsonProperty("produto_id")
        String produtoId,

        @JsonProperty("quantidade")
        BigDecimal quantidade
) {
}
