package br.com.lgmanagement.lgManagement.infra.persistence.interfaces.itransacao;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntity;
import jakarta.persistence.MappedSuperclass;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@MappedSuperclass
public interface ITransacaoEntity {

    void addProdutoToList(ItemEntity item);

    void removeProdutoFromList(ItemEntity item);

    void setFinalizado();

    void setPendente();

    void setSchedule();

    void setUnscheduled();

    String getId();

    List<ItemEntity> getItens();

    BigDecimal getTotal();

    TransacaoStatus getTransacaoStatus();

    TransacaoType getTransacaoType();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    LocalDateTime getScheduledAt();

    Boolean getIsScheduled();

    FuncionarioEntity getFuncionarioEntity();


}
