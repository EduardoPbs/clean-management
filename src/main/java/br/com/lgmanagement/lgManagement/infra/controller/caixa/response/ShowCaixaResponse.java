package br.com.lgmanagement.lgManagement.infra.controller.caixa.response;

import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowCaixaResponse(

        @JsonProperty("valor_abertura")
        BigDecimal valorAbertura,

        @JsonProperty("valor_atual")
        BigDecimal valorAtual,

        @JsonProperty("valor_fechamento")
        BigDecimal valorFechamento,

        LocalDateTime abertura,
        LocalDateTime fechamento
) {
    public ShowCaixaResponse(Caixa caixa) {
        this(
                caixa.getValorAbertura(),
                caixa.getValorAtual(),
                caixa.getValorFechamento(),
                caixa.getAbertura(),
                caixa.getFechamento()
        );
    }
}
