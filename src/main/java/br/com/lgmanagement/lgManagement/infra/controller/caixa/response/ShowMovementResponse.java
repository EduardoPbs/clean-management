package br.com.lgmanagement.lgManagement.infra.controller.caixa.response;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowMovementResponse(
        BigDecimal valor,

        @JsonProperty("tipo_transacao")
        TransacaoType transacaoType,

        @JsonProperty("criado_em")
        LocalDateTime createdAt
) {
}
