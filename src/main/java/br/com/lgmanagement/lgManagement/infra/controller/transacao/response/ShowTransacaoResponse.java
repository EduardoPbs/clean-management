package br.com.lgmanagement.lgManagement.infra.controller.transacao.response;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ShowTransacaoResponse(
        String id,

        @JsonProperty("funcionario_id")
        String funcionarioId,

        @JsonProperty("status")
        TransacaoStatus transacaoStatus,

        @JsonProperty("tipo")
        TransacaoType transacaoType,

//        List<ShowItemResponse> itens,

        @JsonProperty("quantidade_itens")
        Integer quantidadeItens,
        @JsonProperty("criado_em")
        LocalDateTime createdAt,
        LocalDateTime agenda,
        BigDecimal total
) {
    public ShowTransacaoResponse(Transacao transacao, String funcionarioId, ProdutoEntityMapper produtoEntityMapper) {
        this(
                transacao.getId(),
                funcionarioId,
                transacao.getTransacaoStatus(),
                transacao.getTransacaoType(),
                transacao.getItens().size(),
//                transacao.getItens().stream().map(item -> new ShowItemResponse(
//                                item.getQuantidade(),
//                                produtoEntityMapper.toEntity(item.getProduto())
//                        )
//                ).toList(),
                transacao.getCreatedAt(),
                transacao.getScheduledAt(),
                transacao.getTotal()
        );
    }
}
