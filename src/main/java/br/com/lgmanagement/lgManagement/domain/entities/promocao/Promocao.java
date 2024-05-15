package br.com.lgmanagement.lgManagement.domain.entities.promocao;

import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Promocao {

    private String id;
    private Produto produto;
    private Boolean ativo;
    private BigDecimal porcentagemDesconto;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    public Promocao() {

    }

    public Promocao(
            String id,
            Produto produto,
            Boolean ativo,
            BigDecimal porcentagemDesconto,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        this.id = id;
        this.produto = produto;
        this.ativo = ativo;
        this.porcentagemDesconto = porcentagemDesconto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public BigDecimal getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    @Override
    public String toString() {
        return "Promocao{" +
                "id='" + id + '\'' +
                ", produtoId='" + produto + '\'' +
                ", ativo=" + ativo +
                ", porcentagemDesconto=" + porcentagemDesconto +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }
}
