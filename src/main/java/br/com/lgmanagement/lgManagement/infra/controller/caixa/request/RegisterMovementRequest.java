package br.com.lgmanagement.lgManagement.infra.controller.caixa.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegisterMovementRequest(
        @NotNull
        BigDecimal valor
) {
}
