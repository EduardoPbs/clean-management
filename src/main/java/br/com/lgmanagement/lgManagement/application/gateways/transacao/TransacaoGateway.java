package br.com.lgmanagement.lgManagement.application.gateways.transacao;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public interface TransacaoGateway {

    Transacao registrarTransacao(List<Item> itens, String funcionarioId, TransacaoType transacaoType);

    List<Transacao> listarTransacoes();

    List<Transacao> mostrarTransacoesPorFuncionarioId(String id);

    Transacao mostrarTransacao(String id);

    List<Transacao> mostrarTransacoesPorTipo(TransacaoType transacaoType);

    List<Transacao> showTransacoesByStatus(TransacaoStatus transacaoStatus);

    Transacao registerScheduledTransacao(List<Item> itens, String funcionarioId, TransacaoType transacaoType, String dateString);

    void finalizeTransaction(String id);

    void finalizeScheduledTransacations();

    List<Item> showTransactionItems(String id);

    List<Transacao> findTransactionsByDate(int month, int day);

    List<Transacao> findTransactionsByMonth(int month);
}
