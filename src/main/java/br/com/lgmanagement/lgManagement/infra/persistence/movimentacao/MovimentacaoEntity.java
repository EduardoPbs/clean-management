package br.com.lgmanagement.lgManagement.infra.persistence.movimentacao;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "movimentacoes")
@Entity
@EqualsAndHashCode
public class MovimentacaoEntity {

    @Id
    private String id;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TransacaoType transacaoType;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    private CaixaEntity caixa;

    public MovimentacaoEntity() {
    }

    public MovimentacaoEntity(
            String id,
            BigDecimal valor,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.valor = valor;
        this.createdAt = createdAt;
    }

    public MovimentacaoEntity(
            String id,
            BigDecimal valor,
            TransacaoType transacaoType,
            LocalDateTime createdAt,
            CaixaEntity caixa
    ) {
        this.id = id;
        this.valor = valor;
        this.transacaoType = transacaoType;
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

    public TransacaoType getTransacaoType() {
        return transacaoType;
    }

    public void setTransacaoType(TransacaoType transacaoType) {
        this.transacaoType = transacaoType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CaixaEntity getCaixa() {
        return caixa;
    }

    public void setCaixa(CaixaEntity caixa) {
        this.caixa = caixa;
    }

    @Override
    public String toString() {
        return "MovimentacaoEntity{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", createdAt=" + createdAt +
                ", caixa=" + caixa +
                '}';
    }
}
