package br.com.lgmanagement.lgManagement.domain.entities.interfaces.itransacao;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ITransacao {

    void addProdutoToList(Item item);

    void removeProdutoFromList(Item item);

    void setFinalizado();

    void setPendente();

    void setSchedule();

    void setUnscheduled();

    String getId();

    List<Item> getItens();

    BigDecimal getTotal();

    TransacaoStatus getTransacaoStatus();

    TransacaoType getTransacaoType();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    LocalDateTime getScheduledAt();

    Boolean getIsScheduled();

    Funcionario getFuncionario();

}
