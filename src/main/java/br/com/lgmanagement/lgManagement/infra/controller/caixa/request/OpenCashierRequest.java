package br.com.lgmanagement.lgManagement.infra.controller.caixa.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OpenCashierRequest(
        @NotNull
        @JsonProperty("valor_abertura")
        BigDecimal valorAbertura
) {
}
