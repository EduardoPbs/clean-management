package br.com.lgmanagement.lgManagement.domain.entities;

public enum PagamentoType {
    CARTAO_CREDTITO, // Errado pois Postgre não aceita alterções depois da criação da tabela, irá se resolver quando a tabela for dropada e recriada.
    CARTAO_DEBITO,
    PIX,
    DINHEIRO,
    NAO_DEFINIDO,

}
