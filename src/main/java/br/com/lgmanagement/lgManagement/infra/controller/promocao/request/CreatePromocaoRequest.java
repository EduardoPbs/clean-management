package br.com.lgmanagement.lgManagement.infra.controller.promocao.request;

import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreatePromocaoRequest(

        @NotNull
        @JsonProperty("porcentagem_desconto")
        BigDecimal pocentagemDesconto,

        @NotNull
        @JsonProperty("inicio")
        @FutureOrPresent(message = "Deve ser uma data futura.")
        LocalDateTime dataInicio,

        @NotNull
        @JsonProperty("fim")
        LocalDateTime dataFim
) {
//    @AssertTrue(message = "A data de fim deve ser maior que a data de início.")
//    private Boolean dataFinalIsHigher() {
//        if (dataInicio == null || dataFim == null) {
//            return Boolean.FALSE;
//        }
//        return dataFim.isAfter(dataInicio);
//    }
}
