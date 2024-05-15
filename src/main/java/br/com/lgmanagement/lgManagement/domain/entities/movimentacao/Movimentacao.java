package br.com.lgmanagement.lgManagement.domain.entities.movimentacao;

import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Movimentacao {

    private String id;
    private BigDecimal valor;
    private LocalDateTime createdAt;
    private Caixa caixa;

    public Movimentacao() {
    }

    public Movimentacao(BigDecimal valor, Caixa caixa) {
        this.id = UUID.randomUUID().toString();
        this.valor = valor;
        this.createdAt = LocalDateTime.now();
        this.caixa = caixa;
    }

    public Movimentacao(String id, BigDecimal valor, LocalDateTime createdAt, Caixa caixa) {
        this.id = id;
        this.valor = valor;
        this.createdAt = createdAt;
        this.caixa = caixa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", createdAt=" + createdAt +
                ", caixa=" + caixa +
                '}';
    }
}
