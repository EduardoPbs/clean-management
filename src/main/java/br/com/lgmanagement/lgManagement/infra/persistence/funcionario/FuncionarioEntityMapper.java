package br.com.lgmanagement.lgManagement.infra.persistence.funcionario;

import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FuncionarioEntityMapper {

    private final UsuarioEntityMapper usuarioEntityMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final TransacaoEntityMapper transacaoEntityMapper;
    private final ProdutoEntityMapper produtoEntityMapper;

    public FuncionarioEntityMapper(
            UsuarioEntityMapper usuarioEntityMapper,
            FuncionarioRepository funcionarioRepository,
            TransacaoEntityMapper transacaoEntityMapper,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        this.usuarioEntityMapper = usuarioEntityMapper;
        this.funcionarioRepository = funcionarioRepository;

        this.transacaoEntityMapper = transacaoEntityMapper;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    public FuncionarioEntity toEntity(Funcionario funcionario) {
        Optional<FuncionarioEntity> existsFuncionario =
                funcionarioRepository.findByCpf(funcionario.getCpf());

        List<TransacaoEntity> vendaEntities = new ArrayList<>();

        if (funcionario.getVendas() != null) {
            vendaEntities.addAll(funcionario.getVendas()
                    .stream().map(venda -> transacaoEntityMapper.toEntity(venda))
                    .collect(Collectors.toUnmodifiableList()));
        }

        if (existsFuncionario.isEmpty()) {
            return new FuncionarioEntity(
                    funcionario.getNome(),
                    funcionario.getCpf(),
                    funcionario.getEndereco(),
                    usuarioEntityMapper.toEntity(funcionario.getUsuario()),
                    vendaEntities
            );
        }

        return new FuncionarioEntity(
                existsFuncionario.get().getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEndereco(),
                usuarioEntityMapper.toEntity(funcionario.getUsuario()),
                existsFuncionario.get().getVendas()
        );
    }

    public Funcionario toDomain(FuncionarioEntity funcionarioEntity) {
        List<Transacao> transacaos = new ArrayList<>();

        if (funcionarioEntity.getVendas() != null) {
            transacaos.addAll(funcionarioEntity.getVendas()
                    .stream().map(transacaoEntity -> {
                        List<Item> itemTransacoes = transacaoEntity.getItens()
                                .stream().map(itemEntity -> new Item(
                                        itemEntity.getQuantidade(),
                                        produtoEntityMapper.toDomain((ProdutoEntity) itemEntity.getProdutoEntity())
                                ))
                                .collect(Collectors.toUnmodifiableList());

                        return new Transacao(
                                transacaoEntity.getId(),
                                itemTransacoes,
                                transacaoEntity.getTransacaoStatus(),
                                transacaoEntity.getTransacaoType(),
                                transacaoEntity.getCreatedAt(),
                                transacaoEntity.getScheduledAt(),
                                new Funcionario(
                                        funcionarioEntity.getNome(),
                                        funcionarioEntity.getCpf(),
                                        funcionarioEntity.getEndereco(),
                                        new Usuario(
                                                funcionarioEntity.getUsuarioEntity().getEmail(),
                                                funcionarioEntity.getUsuarioEntity().getPassword()
                                        ),
                                        new ArrayList<>()
                                )
                        );
                    })
                    .collect(Collectors.toUnmodifiableList())
            );
        }

        return new Funcionario(
                funcionarioEntity.getNome(),
                funcionarioEntity.getCpf(),
                funcionarioEntity.getEndereco(),
                usuarioEntityMapper.toDomain(funcionarioEntity.getUsuarioEntity()),
                transacaos
        );
    }
}
