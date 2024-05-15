package br.com.lgmanagement.lgManagement.infra.persistence.caixa;

import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "caixa")
@Entity
@EqualsAndHashCode
public class CaixaEntity {

    @Id
    private String id;

    private BigDecimal valorAbertura;
    private BigDecimal valorAtual;
    private BigDecimal valorFechamento;

    @CreatedDate
    private LocalDateTime abertura;
    private LocalDateTime fechamento;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL)
    private List<MovimentacaoEntity> movimentacoes;

    public CaixaEntity() {
    }

    public CaixaEntity(
            BigDecimal valorAbertura
    ) {
        this.id = UUID.randomUUID().toString();
        this.valorAbertura = valorAbertura;
        this.valorAtual = valorAbertura;
        this.valorFechamento = BigDecimal.ZERO;
        this.abertura = LocalDateTime.now();
    }

    public CaixaEntity(
            BigDecimal valorAbertura,
            BigDecimal valorAtual,
            BigDecimal valorFechamento,
            LocalDateTime abertura,
            LocalDateTime fechamento
    ) {
        this.id = UUID.randomUUID().toString();
        this.valorAbertura = valorAbertura;
        this.valorAtual = valorAtual;
        this.valorFechamento = valorFechamento;
        this.abertura = abertura;
        this.fechamento = fechamento;
    }

    public CaixaEntity(
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<MovimentacaoEntity> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoEntity> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    @Override
    public String toString() {
        return "CaixaEntity{" +
                "id='" + id + '\'' +
                ", valorAbertura=" + valorAbertura +
                ", valorFechamento=" + valorFechamento +
                ", abertura=" + abertura +
                ", fechamento=" + fechamento +
                ", movimentacoes=" + movimentacoes +
                '}';
    }
}
