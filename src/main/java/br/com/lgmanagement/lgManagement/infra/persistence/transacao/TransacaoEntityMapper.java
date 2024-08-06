package br.com.lgmanagement.lgManagement.infra.persistence.transacao;

import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoEntityMapper {

    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    public TransacaoEntityMapper(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    public TransacaoEntity toEntity(Transacao transacao) {
        List<ItemEntity> mercadoriaEntities = transacao.getItens()
                .stream().map(item -> {
                    ProdutoEntity produtoEntity = produtoEntityMapper
                            .toEntity((Produto) item.getProduto());

                    ProdutoEntity mercadoriaFound = produtoRepository
                            .findByCodigo(produtoEntity.getCodigo())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));


                    return new ItemEntity(
                            item.getQuantidade(),
                            mercadoriaFound
                    );
                })
                .collect(Collectors.toUnmodifiableList());

        TransacaoEntity transacaoEntity = new TransacaoEntity(
                mercadoriaEntities,
                transacao.getTransacaoStatus(),
                transacao.getTransacaoType(),
                transacao.getPagamentoType()
        );
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity(
                transacao.getFuncionario().getNome(),
                transacao.getFuncionario().getCpf(),
                transacao.getFuncionario().getEndereco(),
                new UsuarioEntity(
                        transacao.getFuncionario().getUsuario().getEmail(),
                        transacao.getFuncionario().getUsuario().getPassword(),
                        transacao.getFuncionario().getUsuario().isAdmin()
                ),
                new ArrayList<>()
        );
        BigDecimal total = mercadoriaEntities.stream()
                .map(item -> item.getQuantidade().multiply(item.getValorUnitario()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        transacaoEntity.setFuncionarioEntity(funcionarioEntity);
        transacaoEntity.setTotal(total);

        return transacaoEntity;
    }

    public Transacao toDomain(TransacaoEntity transacaoEntity) {
        List<Item> itemTransacoes = transacaoEntity.getItens()
                .stream().map(itemEntity -> new Item(
                        itemEntity.getQuantidade(),
                        produtoEntityMapper.toDomain(itemEntity.getProdutoEntity())
                ))
                .collect(Collectors.toUnmodifiableList());

        Transacao transacao = new Transacao(
                transacaoEntity.getId(),
                itemTransacoes,
                transacaoEntity.getTransacaoStatus(),
                transacaoEntity.getTransacaoType(),
                transacaoEntity.getPagamentoType(),
                transacaoEntity.getCreatedAt(),
                transacaoEntity.getScheduledAt(),
                transacaoEntity.getTotal()
        );
        Funcionario funcionario = new Funcionario(
                transacaoEntity.getFuncionarioEntity().getNome(),
                transacaoEntity.getFuncionarioEntity().getCpf(),
                transacaoEntity.getFuncionarioEntity().getEndereco(),
                new Usuario(
                        transacaoEntity.getFuncionarioEntity().getUsuarioEntity().getEmail(),
                        transacaoEntity.getFuncionarioEntity().getUsuarioEntity().getPassword()
                )
        );
        transacao.setFuncionario(funcionario);

        return transacao;
    }
}
