package br.com.lgmanagement.lgManagement.application.facades.transacoes;

import br.com.lgmanagement.lgManagement.application.usecases.transacao.*;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;

import java.util.List;

public class ShowTransactionsFacade {

    private final ShowAllTransacoesInteractor showAllTransacoesInteractor;
    private final ShowTransacoesByFuncionarioIdInteractor showTransacoesByFuncionarioIdInteractor;
    private final ShowOneTransacaoInteractor showOneTransacaoInteractor;
    private final ShowTransacoesByTypeInteractor showTransacoesByTypeInteractor;
    private final ShowTransacoesByStatusInteractor showTransacoesByStatusInteractor;
    private final ShowTransacoesByDateInteractor showTransacoesByDateInteractor;
    private final ShowTransacoesByMonthInteractor showTransacoesByMonthInteractor;

    public ShowTransactionsFacade(
            ShowAllTransacoesInteractor showAllTransacoesInteractor,
            ShowTransacoesByFuncionarioIdInteractor showTransacoesByFuncionarioIdInteractor,
            ShowOneTransacaoInteractor showOneTransacaoInteractor,
            ShowTransacoesByTypeInteractor showTransacoesByTypeInteractor,
            ShowTransacoesByStatusInteractor showTransacoesByStatusInteractor,
            ShowTransacoesByDateInteractor showTransacoesByDateInteractor,
            ShowTransacoesByMonthInteractor showTransacoesByMonthInteractor
    ) {
        this.showAllTransacoesInteractor = showAllTransacoesInteractor;
        this.showTransacoesByFuncionarioIdInteractor = showTransacoesByFuncionarioIdInteractor;
        this.showOneTransacaoInteractor = showOneTransacaoInteractor;
        this.showTransacoesByTypeInteractor = showTransacoesByTypeInteractor;
        this.showTransacoesByStatusInteractor = showTransacoesByStatusInteractor;
        this.showTransacoesByDateInteractor = showTransacoesByDateInteractor;
        this.showTransacoesByMonthInteractor = showTransacoesByMonthInteractor;
    }

    public List<Transacao> all() {
        return showAllTransacoesInteractor.mostrarTransacoes();
    }

    public List<Transacao> allByFuncionarioId(String id) {
        return showTransacoesByFuncionarioIdInteractor.mostrarTransacoesPorFuncionarioId(id);
    }

    public Transacao viewById(String id) {
        return showOneTransacaoInteractor.mostrarTransacao(id);
    }

    public List<Transacao> allByType(TransacaoType transacaoType) {
        return showTransacoesByTypeInteractor.mostrarTransacoesPorTipo(transacaoType);
    }

    public List<Transacao> allByStatus(TransacaoStatus transacaoStatus) {
        return showTransacoesByStatusInteractor.showTransacoesByStatus(transacaoStatus);
    }

    public List<Transacao> allByDate(int month, int day) {
        return showTransacoesByDateInteractor.showTransacoesByDate(month, day);
    }

    public List<Transacao> allByMonth(int month) {
        return showTransacoesByMonthInteractor.showTransactionsByMonth(month);
    }
}
