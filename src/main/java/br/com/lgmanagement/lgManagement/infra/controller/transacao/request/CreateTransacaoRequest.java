package br.com.lgmanagement.lgManagement.infra.controller.transacao.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateTransacaoRequest(
        List<CreateItemRequest> itens,

        @JsonProperty("funcionario_id")
        String funcionarioId
) {
}
