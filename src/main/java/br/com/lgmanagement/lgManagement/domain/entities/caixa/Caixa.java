package br.com.lgmanagement.lgManagement.domain.entities.caixa;

import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Caixa {

    private String id;
    private BigDecimal valorAbertura;
    private BigDecimal valorAtual;
    private BigDecimal valorFechamento;
    private LocalDateTime abertura;
    private LocalDateTime fechamento;
    private LocalDateTime updatedAt;
    private List<Movimentacao> movimentacoes;

    public Caixa() {
    }

    public Caixa(
            String id,
            BigDecimal valorAbertura,
            BigDecimal valorAtual,
            BigDecimal valorFechamento,
            LocalDateTime abertura,
            LocalDateTime fechamento
    ) {
        this.id = id;
        this.valorAbertura = valorAbertura;
        this.valorAtual = valorAtual;
        this.valorFechamento = valorFechamento;
        this.abertura = abertura;
        this.fechamento = fechamento;
    }

    public Caixa(
            String id,
            BigDecimal valorAbertura,
            BigDecimal valorAtual,
            BigDecimal valorFechamento,
            LocalDateTime abertura,
            LocalDateTime fechamento,
            List<Movimentacao> movimentacoes
    ) {
        this.id = id;
        this.valorAbertura = valorAbertura;
        this.valorAtual = valorAtual;
        this.valorFechamento = valorFechamento;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.movimentacoes = movimentacoes;
    }

    public Caixa(
            BigDecimal valorAbertura,
            BigDecimal valorAtual,
            BigDecimal valorFechamento,
            LocalDateTime abertura,
            LocalDateTime fechamento,
            List<Movimentacao> movimentacoes
    ) {
        this.id = UUID.randomUUID().toString();
        this.valorAbertura = valorAbertura;
        this.valorAtual = valorAtual;
        this.valorFechamento = valorFechamento;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.movimentacoes = movimentacoes;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(BigDecimal valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public BigDecimal getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(BigDecimal valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public LocalDateTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDateTime abertura) {
        this.abertura = abertura;
    }

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDateTime fechamento) {
        this.fechamento = fechamento;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    @Override
    public String toString() {
        return "Caixa{" +
                "valorAbertura=" + valorAbertura +
                ", valorAtual=" + valorAtual +
                ", valorFechamento=" + valorFechamento +
                ", abertura=" + abertura +
                ", fechamento=" + fechamento +
                ", movimentacoes=" + movimentacoes +
                '}';
    }

}
