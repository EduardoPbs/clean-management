package br.com.lgmanagement.lgManagement.domain.entities.transacao;

import br.com.lgmanagement.lgManagement.domain.entities.PagamentoType;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.domain.entities.interfaces.itransacao.ITransacao;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Transacao implements ITransacao {

    private String id;
    private Integer code;
    private List<Item> itens;
    private BigDecimal total;
    private TransacaoStatus transacaoStatus;
    private TransacaoType transacaoType;
    private PagamentoType pagamentoType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime scheduledAt;
    private Boolean isScheduled;
    private Funcionario funcionario;

    public Transacao() {
    }

    public Transacao(
            String id,
            List<Item> itens,
            TransacaoStatus transacaoStatus,
            TransacaoType transacaoType,
            PagamentoType pagamentoType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Funcionario funcionario
    ) {
        this.id = id;
        this.itens = itens;
        this.transacaoStatus = transacaoStatus;
        this.transacaoType = transacaoType;
        this.pagamentoType = pagamentoType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.funcionario = funcionario;
    }

    public Transacao(
            String id,
            Integer code,
            List<Item> itens,
            TransacaoStatus transacaoStatus,
            TransacaoType transacaoType,
            PagamentoType pagamentoType,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Funcionario funcionario
    ) {
        this.id = id;
        this.code = code;
        this.itens = itens;
        this.transacaoStatus = transacaoStatus;
        this.transacaoType = transacaoType;
        this.pagamentoType = pagamentoType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.funcionario = funcionario;
    }

//    public Transacao(
//            String id,
//            List<Item> itens,
//            TransacaoStatus transacaoStatus,
//            TransacaoType transacaoType,
//            Funcionario funcionario
//    ) {
//        this.id = id;
//        this.itens = itens;
//        this.transacaoStatus = transacaoStatus;
//        this.transacaoType = transacaoType;
//        this.funcionario = funcionario;
//    }

//    public Transacao(
//            String id,
//            List<Item> itens,
//            TransacaoStatus transacaoStatus,
//            TransacaoType transacaoType,
//            BigDecimal total
//    ) {
//        this.id = id;
//        this.itens = itens;
//        this.transacaoStatus = transacaoStatus;
//        this.transacaoType = transacaoType;
//        this.total = total;
//    }

    public Transacao(
            String id,
            List<Item> itens,
            TransacaoStatus transacaoStatus,
            TransacaoType transacaoType,
            PagamentoType pagamentoType,
            LocalDateTime createdAt,
            LocalDateTime scheduledAt,
            BigDecimal total
    ) {
        this.id = id;
        this.itens = itens;
        this.transacaoStatus = transacaoStatus;
        this.transacaoType = transacaoType;
        this.pagamentoType = pagamentoType;
        this.createdAt = createdAt;
        this.scheduledAt = scheduledAt;
        this.total = total;
    }

    public Transacao(
            String id,
            Integer code,
            List<Item> itens,
            TransacaoStatus transacaoStatus,
            TransacaoType transacaoType,
            PagamentoType pagamentoType,
            LocalDateTime createdAt,
            LocalDateTime scheduledAt,
            BigDecimal total
    ) {
        this.id = id;
        this.code = code;
        this.itens = itens;
        this.transacaoStatus = transacaoStatus;
        this.transacaoType = transacaoType;
        this.pagamentoType = pagamentoType;
        this.createdAt = createdAt;
        this.scheduledAt = scheduledAt;
        this.total = total;
    }

    @Override
    public void addProdutoToList(Item item) {
        this.itens.add(item);
    }

    @Override
    public void removeProdutoFromList(Item item) {
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
    public List<Item> getItens() {
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
        return this.isScheduled;
    }

    @Override
    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setTransacaoStatus(TransacaoStatus transacaoStatus) {
        this.transacaoStatus = transacaoStatus;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setScheduledAt(LocalDateTime scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "isScheduled=" + isScheduled +
                ", scheduledAt=" + scheduledAt +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", transacaoType=" + transacaoType +
                ", transacaoStatus=" + transacaoStatus +
                ", total=" + total +
                ", itens=" + itens +
                ", id='" + id + '\'' +
                '}';
    }
}
