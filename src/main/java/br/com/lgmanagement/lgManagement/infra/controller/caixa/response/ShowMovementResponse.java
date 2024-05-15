package br.com.lgmanagement.lgManagement.infra.controller.caixa.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowMovementResponse(
        BigDecimal valor,

        @JsonProperty("criado_em")
        LocalDateTime createdAt
) {
}
