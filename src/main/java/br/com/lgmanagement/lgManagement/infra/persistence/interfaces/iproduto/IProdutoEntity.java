package br.com.lgmanagement.lgManagement.infra.persistence.interfaces.iproduto;

import java.math.BigDecimal;

public interface IProdutoEntity {

    void delete();

    void restore();

    BigDecimal getValor();
}
