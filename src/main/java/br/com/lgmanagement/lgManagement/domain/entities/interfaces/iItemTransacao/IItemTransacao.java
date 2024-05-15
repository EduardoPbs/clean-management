package br.com.lgmanagement.lgManagement.domain.entities.interfaces.iItemTransacao;

import br.com.lgmanagement.lgManagement.domain.entities.interfaces.iproduto.IProduto;
import br.com.lgmanagement.lgManagement.domain.entities.interfaces.itransacao.ITransacao;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.math.BigDecimal;

public interface IItemTransacao {

    String getId();

    BigDecimal getValorUnitario();

    BigDecimal getQuantidade();

    IProduto getProduto();

    ITransacao getTransacao();

    BigDecimal calcularTotal();

    void setTransacao(Transacao transacao);
}

