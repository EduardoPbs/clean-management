package br.com.lgmanagement.lgManagement.infra.persistence.caixa;

import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;
import br.com.lgmanagement.lgManagement.infra.persistence.movimentacao.MovimentacaoEntityMapper;

import java.util.ArrayList;
import java.util.List;

public class CaixaEntityMapper {

    private final MovimentacaoEntityMapper movimentacaoEntityMapper;

    public CaixaEntityMapper(MovimentacaoEntityMapper movimentacaoEntityMapper) {
        this.movimentacaoEntityMapper = movimentacaoEntityMapper;
    }

    public Caixa toDomain(CaixaEntity caixaEntity) {
        List<Movimentacao> movimentacoes = new ArrayList<>();

        if (caixaEntity.getMovimentacoes() != null) {
            movimentacoes.addAll(caixaEntity.getMovimentacoes().stream()
                    .map(movimentacaoEntity -> movimentacaoEntityMapper.toDomain(movimentacaoEntity))
                    .toList());
        }

        return new Caixa(
                caixaEntity.getValorAbertura(),
                caixaEntity.getValorAtual(),
                caixaEntity.getValorFechamento(),
                caixaEntity.getAbertura(),
                caixaEntity.getFechamento(),
                movimentacoes
        );
    }

    public CaixaEntity toEntity(Caixa caixa) {
        return new CaixaEntity(
                caixa.getValorAbertura(),
                caixa.getValorAtual(),
                caixa.getValorFechamento(),
                caixa.getAbertura(),
                caixa.getFechamento()
        );
    }
}
