package br.com.lgmanagement.lgManagement.infra.persistence.transacao;

import br.com.lgmanagement.lgManagement.domain.entities.PagamentoType;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.interfaces.itransacao.ITransacaoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "transacoes")
@Entity
@EqualsAndHashCode(of = "id")
public class TransacaoEntity implements ITransacaoEntity {

    @Id
    private String id;

    @Transient
    private static Integer codeCounter = 0;

    private Integer code;

    @OneToMany(mappedBy = "transacao", cascade = CascadeType.ALL)
    private List<ItemEntity> itens;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private TransacaoStatus transacaoStatus;

    @Enumerated(EnumType.STRING)
    private TransacaoType transacaoType;

    @Enumerated(EnumType.STRING)
    private PagamentoType pagamentoType;

    @CreatedDate
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime scheduledAt;
    private Boolean isScheduled;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    private FuncionarioEntity funcionarioEntity;

    public TransacaoEntity() {
        this.id = UUID.randomUUID().toString();
        this.code = incrementCode();
        this.createdAt = LocalDateTime.now();
        this.transacaoStatus = TransacaoStatus.PENDENTE;
    }

    public TransacaoEntity(
            List<ItemEntity> itens,
            TransacaoStatus transacaoStatus,
            TransacaoType transacaoType,
            PagamentoType pagamentoType
    ) {
        this.id = UUID.randomUUID().toString();
        this.code = incrementCode();
        this.itens = itens;
        this.transacaoStatus = transacaoStatus;
        this.transacaoType = transacaoType;
        this.pagamentoType = pagamentoType;
    }

    public TransacaoEntity(
            List<ItemEntity> itens,
            Integer code,
            TransacaoStatus transacaoStatus,
            TransacaoType transacaoType,
            PagamentoType pagamentoType
    ) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.itens = itens;
        this.transacaoStatus = transacaoStatus;
        this.transacaoType = transacaoType;
        this.pagamentoType = pagamentoType;
    }

    private synchronized static Integer incrementCode() {
        return ++codeCounter;
    }

    @Override
    public void addProdutoToList(ItemEntity item) {
        this.itens.add(item);
    }

    @Override
    public void removeProdutoFromList(ItemEntity item) {
        this.itens.remove(item);
    }

    @Override
    public void setFinalizado() {
        this.transacaoStatus = TransacaoStatus.FINALIZADO;
    }

    @Override
    public void setPendente() {
        this.transacaoStatus = TransacaoStatus.PENDENTE;
    }

    @Override
    public void setSchedule() {
        this.isScheduled = true;
    }

    @Override
    public void setUnscheduled() {
        this.isScheduled = false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public List<ItemEntity> getItens() {
        return this.itens;
    }

    @Override
    public BigDecimal getTotal() {
        return this.total;
    }

    @Override
    public TransacaoStatus getTransacaoStatus() {
        return this.transacaoStatus;
    }

    @Override
    public TransacaoType getTransacaoType() {
        return this.transacaoType;
    }

    public PagamentoType getPagamentoType() {
        return pagamentoType;
    }

    public void setPagamentoType(PagamentoType pagamentoType) {
        this.pagamentoType = pagamentoType;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public LocalDateTime getScheduledAt() {
        return this.scheduledAt;
    }

    @Override
    public Boolean getIsScheduled() {
        return null;
    }

    @Override
    public FuncionarioEntity getFuncionarioEntity() {
        return this.funcionarioEntity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItens(List<ItemEntity> itens) {
        this.itens = itens;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setFuncionarioEntity(FuncionarioEntity funcionarioEntity) {
        this.funcionarioEntity = funcionarioEntity;
    }

    public void setTransacaoStatus(TransacaoStatus transacaoStatus) {
        this.transacaoStatus = transacaoStatus;
    }

    public void setTransacaoType(TransacaoType transacaoType) {
        this.transacaoType = transacaoType;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    @Override
    public String toString() {
        return "TransacaoEntity{" +
                "id='" + id + '\'' +
                ", itens=" + itens +
                ", total=" + total +
                ", transacaoStatus=" + transacaoStatus +
                ", transacaoType=" + transacaoType +
                ", pagamentoType=" + pagamentoType +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", scheduledAt=" + scheduledAt +
                ", isScheduled=" + isScheduled +
                '}';
    }
}
