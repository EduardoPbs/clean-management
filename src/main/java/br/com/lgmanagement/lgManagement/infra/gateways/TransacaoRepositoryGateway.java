package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.gateways.transacao.TransacaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransacaoRepositoryGateway implements TransacaoGateway {

    private final TransacaoRepository transacaoRepository;
    private final TransacaoEntityMapper transacaoEntityMapper;
    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final ItemEntityMapper itemEntityMapper;
    private final ItemRepository itemRepository;
    private final MovimentacaoRepositoryGateway movimentacaoRepositoryGateway;
    private final CaixaRepositoryGateway caixaRepositoryGateway;

    public TransacaoRepositoryGateway(
            TransacaoRepository transacaoRepository,
            TransacaoEntityMapper transacaoEntityMapper,
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper,
            FuncionarioRepository funcionarioRepository,
            ItemEntityMapper itemEntityMapper,
            ItemRepository itemRepository,
            MovimentacaoRepositoryGateway movimentacaoRepositoryGateway,
            CaixaRepositoryGateway caixaRepositoryGateway
    ) {
        this.transacaoRepository = transacaoRepository;
        this.transacaoEntityMapper = transacaoEntityMapper;
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.itemEntityMapper = itemEntityMapper;
        this.itemRepository = itemRepository;
        this.movimentacaoRepositoryGateway = movimentacaoRepositoryGateway;
        this.caixaRepositoryGateway = caixaRepositoryGateway;
    }

    @Override
    @Transactional
    public Transacao registrarTransacao(List<Item> itens, String funcionarioId, TransacaoType transacaoType) {
        Transacao transacao = registerTransaction(itens, funcionarioId, transacaoType, null);
        return transacao;
    }

    @Override
    public List<Transacao> listarTransacoes() {
        List<Transacao> transacaoList = transacaoRepository.findAll().stream().map(transacaoEntity -> {
            FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(transacaoEntity.getFuncionarioEntity().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));
            Transacao transacao = new Transacao(
                    transacaoEntity.getId(),
                    transacaoEntity.getItens().stream().map(itemEntity -> new Item(
                            itemEntity.getQuantidade(),
                            produtoEntityMapper.toDomain(itemEntity.getProdutoEntity())
                    )).toList(),
                    transacaoEntity.getTransacaoStatus(),
                    transacaoEntity.getTransacaoType(),
                    transacaoEntity.getCreatedAt(),
                    transacaoEntity.getScheduledAt(),
                    transacaoEntity.getTotal()
            );

            transacao.setFuncionario(new Funcionario(
                    funcionarioEntity.getNome(),
                    funcionarioEntity.getCpf(),
                    funcionarioEntity.getEndereco(),
                    new Usuario(
                            funcionarioEntity.getUsuarioEntity().getEmail(),
                            funcionarioEntity.getUsuarioEntity().getPassword()
                    ),
                    new ArrayList<>()
            ));
            return transacao;
        }).collect(Collectors.toUnmodifiableList());

        return transacaoList;
    }

    @Override
    public List<Transacao> mostrarTransacoesPorFuncionarioId(String id) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));
        List<TransacaoEntity> vendasEntity = transacaoRepository.findByfuncionarioEntity(funcionarioEntity);
        return vendasEntity.stream().map(vendaEntity ->
                transacaoEntityMapper.toDomain(vendaEntity)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Transacao mostrarTransacao(String id) {
        TransacaoEntity transacaoEntity = transacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transação não encontrada."));

        return transacaoEntityMapper.toDomain(transacaoEntity);
    }

    @Override
    public List<Transacao> mostrarTransacoesPorTipo(TransacaoType transacaoType) {
        List<Transacao> transacoes = transacaoRepository.findByTransacaoType(transacaoType)
                .stream().map(transacaoEntity -> transacaoEntityMapper.toDomain(transacaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return transacoes;
    }

    @Override
    public List<Transacao> showTransacoesByStatus(TransacaoStatus transacaoStatus) {
        List<Transacao> transacoes = transacaoRepository.findByTransacaoStatus(transacaoStatus)
                .stream().map(transacaoEntity -> transacaoEntityMapper.toDomain(transacaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return transacoes;
    }

    @Override
    @Transactional
    public Transacao registerScheduledTransacao(List<Item> itens, String funcionarioId, TransacaoType transacaoType, String dateString) {
        Transacao transacao = registerTransaction(itens, funcionarioId, transacaoType, dateString);
        return transacao;
    }

    @Override
    @Transactional
    public void finalizeTransaction(String id) {
        TransacaoEntity transacaoEntity = transacaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transação não encontrada."));

        if (transacaoEntity.getTransacaoStatus().equals(TransacaoStatus.FINALIZADO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transação já finalizada.");
        }

        transacaoEntity.setTransacaoStatus(TransacaoStatus.FINALIZADO);
        discountProductStockIfTypeVENDA(transacaoEntity);
        transacaoRepository.save(transacaoEntity);
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 300000) // 5 min.
    public void finalizeScheduledTransacations() {
        transacaoRepository.findByIsScheduledTrue().stream()
                .forEach(transacaoEntity -> {
                    if (transacaoEntity.getScheduledAt().isBefore(LocalDateTime.now())) {
                        transacaoEntity.setTransacaoStatus(TransacaoStatus.FINALIZADO);
                        discountProductStockIfTypeVENDA(transacaoEntity);
                        transacaoRepository.save(transacaoEntity);
                    }
                });
    }

    @Override
    public List<Item> showTransactionItems(String id) {
        return itemRepository.findByTransacaoId(id)
                .stream().map(itemEntity -> itemEntityMapper.toDomain(itemEntity))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Transacao> findTransactionsByDate(int month, int day) {
        List<Transacao> transacoes = transacaoRepository.findTransacoesByDate(month, day)
                .stream().map(transacaoEntity -> transacaoEntityMapper.toDomain(transacaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return transacoes;
    }

    @Override
    public List<Transacao> findTransactionsByMonth(int month) {
        List<Transacao> transacoes = transacaoRepository.findTransacoesByMonth(month)
                .stream().map(transacaoEntity -> transacaoEntityMapper.toDomain(transacaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return transacoes;
    }

    private Transacao registerTransaction(List<Item> itens, String funcionarioId, TransacaoType transacaoType, String dateString) {
        TransacaoEntity newTransacaoEntity = new TransacaoEntity();
        if (dateString != null) {
            newTransacaoEntity.setSchedule();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime date = LocalDateTime.parse(dateString, formatter);
                newTransacaoEntity.setScheduledAt(date);
            } catch (Exception exception) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agendamento apenas para datas futuras.");
            }
        } else newTransacaoEntity.setUnscheduled();

        FuncionarioEntity funcionarioEntity = funcionarioRepository
                .findById(funcionarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));

        List<ItemEntity> itemTransacaoEntities = itens
                .stream().map(itemTransacao -> {
                    ProdutoEntity produtoEntity = produtoEntityMapper
                            .toEntity(itemTransacao.getProduto());

                    ProdutoEntity produtoFound = produtoRepository
                            .findByCodigo(produtoEntity.getCodigo())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

                    return new ItemEntity(
                            itemTransacao.getQuantidade(),
                            produtoFound,
                            newTransacaoEntity
                    );
                })
                .collect(Collectors.toUnmodifiableList());

        BigDecimal totalOfItems = itens.stream()
                .map(item -> {
                    ProdutoEntity produtoFound = produtoRepository
                            .findByCodigo(item.getProduto().getCodigo())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

                    return item.getQuantidade().multiply(produtoFound.getValor());
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        movimentacaoRepositoryGateway
                .registerMovement(totalOfItems, caixaRepositoryGateway.showCaixa(), transacaoType);

        newTransacaoEntity.setItens(itemTransacaoEntities);
        newTransacaoEntity.setTotal(totalOfItems);
        newTransacaoEntity.setFuncionarioEntity(funcionarioEntity);
        newTransacaoEntity.setTransacaoType(transacaoType);

        transacaoRepository.save(newTransacaoEntity);

        Transacao transacao = transacaoEntityMapper.toDomain(newTransacaoEntity);
        return transacao;
    }

    private void discountProductStockIfTypeVENDA(TransacaoEntity transacaoEntity) {
        if (transacaoEntity.getTransacaoType().equals(TransacaoType.VENDA)) {
            transacaoEntity.getItens()
                    .stream().forEach(itemEntity -> {
                        ProdutoEntity produtoEntityFound = produtoRepository
                                .findById(itemEntity.getProdutoEntity().getId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

                        if (itemEntity.getQuantidade().compareTo(produtoEntityFound.getEstoque()) > 0) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente." +
                                    " Produto: " + produtoEntityFound.getNome() + ". " +
                                    " Em estoque: " + produtoEntityFound.getEstoque());
                        }

                        produtoEntityFound.setEstoque(
                                produtoEntityFound.getEstoque().subtract(itemEntity.getQuantidade()));
                    });
        }
    }
}
