package br.com.lgmanagement.lgManagement.infra.controller.promocao.response;

import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;
import br.com.lgmanagement.lgManagement.infra.controller.produto.response.ShowProdutoResponse;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowPromocaoResponse(
        String id,

        @JsonProperty("produto")
        ShowProdutoResponse produtoResponse,
        Boolean ativo,

        @JsonProperty("desconto")
        BigDecimal porcentagemDesconto,

        @JsonProperty("data_inicio")
        LocalDateTime dataInicio,

        @JsonProperty("data_fim")
        LocalDateTime dataFim

) {
    public ShowPromocaoResponse(Promocao promocao, ProdutoEntityMapper produtoEntityMapper) {
        this(
                promocao.getId(),
                new ShowProdutoResponse(produtoEntityMapper.toEntity(promocao.getProduto())),
                promocao.getAtivo(),
                promocao.getPorcentagemDesconto(),
                promocao.getDataInicio(),
                promocao.getDataFim()
        );
    }
}

