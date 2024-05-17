package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.gateways.movimentacao.MovimentacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MovimentacaoRepositoryGateway implements MovimentacaoGateway {

    private final MovimentacaoRepository movimentacaoRepository;
    private final MovimentacaoEntityMapper movimentacaoEntityMapper;
    private final CaixaRepository caixaRepository;
    private final CaixaEntityMapper caixaEntityMapper;

    public MovimentacaoRepositoryGateway(
            MovimentacaoRepository movimentacaoRepository,
            MovimentacaoEntityMapper movimentacaoEntityMapper,
            CaixaRepository caixaRepository,
            CaixaEntityMapper caixaEntityMapper
    ) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoEntityMapper = movimentacaoEntityMapper;
        this.caixaRepository = caixaRepository;
        this.caixaEntityMapper = caixaEntityMapper;
    }

    @Override
    public Movimentacao registerMovement(BigDecimal valor, Caixa caixa, TransacaoType transacaoType) {
        CaixaEntity caixaEntity = caixaEntityMapper.toEntity(caixa);
        MovimentacaoEntity movimentacaoEntity = movimentacaoEntityMapper
                .toEntity(new Movimentacao(valor, caixaEntityMapper.toDomain(caixaEntity)));

        if (transacaoType.equals(TransacaoType.VENDA)) {
            caixaEntity.setValorAtual(caixaEntity.getValorAtual().add(movimentacaoEntity.getValor()));
        }

        movimentacaoEntity.setCaixa(caixaEntity);
        caixaRepository.save(caixaEntity);
        movimentacaoRepository.save(movimentacaoEntity);
        return movimentacaoEntityMapper.toDomain(movimentacaoEntity);
    }

    @Override
    public List<Movimentacao> showAllMovements() {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findAll().stream()
                .map(movimentacaoEntity -> movimentacaoEntityMapper.toDomain(movimentacaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return movimentacoes;
    }

    @Override
    public List<Movimentacao> findMovementsByMonth(int month) {
        if (month < 1 || month > 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mês inválido.");
        }

        List<Movimentacao> movimentacoes = movimentacaoRepository.findByMonth(month).stream()
                .map(movimentacaoEntity -> movimentacaoEntityMapper.toDomain(movimentacaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return movimentacoes;
    }

    @Override
    public List<Movimentacao> findMovementsByDate(int month, int day) {
        if (day < 1 || day > 31 && month < 1 || month > 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida.");
        }

        List<Movimentacao> movimentacoes = movimentacaoRepository.findByDate(month, day).stream()
                .map(movimentacaoEntity -> movimentacaoEntityMapper.toDomain(movimentacaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return movimentacoes;
    }
}
