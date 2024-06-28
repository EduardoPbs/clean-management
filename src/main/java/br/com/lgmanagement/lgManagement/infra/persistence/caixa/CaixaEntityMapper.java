package br.com.lgmanagement.lgManagement.infra.persistence.caixa;

import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoEntityMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class CaixaEntityMapper {

    private final MovimentacaoEntityMapper movimentacaoEntityMapper;
    private final CaixaRepository caixaRepository;

    public CaixaEntityMapper(MovimentacaoEntityMapper movimentacaoEntityMapper, CaixaRepository caixaRepository) {
        this.movimentacaoEntityMapper = movimentacaoEntityMapper;
        this.caixaRepository = caixaRepository;
    }

    public Caixa toDomain(CaixaEntity caixaEntity) {
        List<Movimentacao> movimentacoes = new ArrayList<>();

        if (caixaEntity.getMovimentacoes() != null) {
            movimentacoes.addAll(caixaEntity.getMovimentacoes().stream()
                    .map(movimentacaoEntity -> movimentacaoEntityMapper.toDomain(movimentacaoEntity))
                    .toList());
        }

        return new Caixa(
                caixaEntity.getId(),
                caixaEntity.getValorAbertura(),
                caixaEntity.getValorAtual(),
                caixaEntity.getValorFechamento(),
                caixaEntity.getAbertura(),
                caixaEntity.getFechamento(),
                movimentacoes
        );
    }

    public CaixaEntity toEntity(Caixa caixa) {
        CaixaEntity caixaEntity = caixaRepository
                .findById(caixa.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Caixa não encontrado."));

        return new CaixaEntity(
                caixaEntity.getId(),
                caixaEntity.getValorAbertura(),
                caixaEntity.getValorAtual(),
                caixaEntity.getValorFechamento(),
                caixaEntity.getAbertura(),
                caixaEntity.getFechamento()
        );
    }
}
