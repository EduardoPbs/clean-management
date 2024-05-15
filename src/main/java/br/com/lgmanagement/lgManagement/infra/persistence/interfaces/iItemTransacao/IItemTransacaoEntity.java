package br.com.lgmanagement.lgManagement.infra.persistence.interfaces.iItemTransacao;

import br.com.lgmanagement.lgManagement.infra.persistence.interfaces.iproduto.IProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.interfaces.itransacao.ITransacaoEntity;

import java.math.BigDecimal;

public interface IItemTransacaoEntity {

    String getId();

    BigDecimal getValorUnitario();

    BigDecimal getQuantidade();

    IProdutoEntity getProdutoEntity();

    ITransacaoEntity getTransacaoEntity();

    BigDecimal calcularTotal();
}
