package br.com.lgmanagement.lgManagement.infra.persistence.movimentacao;

import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import br.com.lgmanagement.lgManagement.domain.entities.movimentacao.Movimentacao;

public class MovimentacaoEntityMapper {

    public Movimentacao toDomain(MovimentacaoEntity movimentacaoEntity) {
        Caixa caixa = new Caixa(
                movimentacaoEntity.getCaixa().getId(),
                movimentacaoEntity.getCaixa().getValorAbertura(),
                movimentacaoEntity.getCaixa().getValorAtual(),
                movimentacaoEntity.getCaixa().getValorFechamento(),
                movimentacaoEntity.getCaixa().getAbertura(),
                movimentacaoEntity.getCaixa().getFechamento()
        );

        return new Movimentacao(
                movimentacaoEntity.getId(),
                movimentacaoEntity.getValor(),
                movimentacaoEntity.getTransacaoType(),
                movimentacaoEntity.getCreatedAt(),
                caixa
        );
    }

    public MovimentacaoEntity toEntity(Movimentacao movimentacao) {
        return new MovimentacaoEntity(
                movimentacao.getId(),
                movimentacao.getValor(),
                movimentacao.getCreatedAt()
        );
    }
}
