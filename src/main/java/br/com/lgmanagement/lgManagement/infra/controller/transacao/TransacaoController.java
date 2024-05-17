package br.com.lgmanagement.lgManagement.infra.controller.transacao;

import br.com.lgmanagement.lgManagement.application.usecases.transacao.*;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.domain.entities.item.Item;
import br.com.lgmanagement.lgManagement.domain.entities.transacao.Transacao;
import br.com.lgmanagement.lgManagement.infra.controller.transacao.request.CreateItemRequest;
import br.com.lgmanagement.lgManagement.infra.controller.transacao.request.CreateTransacaoRequest;
import br.com.lgmanagement.lgManagement.infra.controller.transacao.response.ShowItemResponse;
import br.com.lgmanagement.lgManagement.infra.controller.transacao.response.ShowTransacaoResponse;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransacaoController {

    private final ProdutoRepository produtoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoEntityMapper produtoEntityMapper;
    private final CreateTransacaoInteractor createTransacaoInteractor;
    private final ShowAllTransacoesInteractor showAllTransacoesInteractor;
    private final ShowTransacoesByFuncionarioIdInteractor showTransacoesByFuncionarioIdInteractor;
    private final ShowOneTransacaoInteractor showOneTransacaoInteractor;
    private final ShowTransacoesByTypeInteractor showTransacoesByTypeInteractor;
    private final ShowTransacoesByStatusInteractor showTransacoesByStatusInteractor;
    private final CreateScheduledTransacaoInteractor createScheduledTransacaoInteractor;
    private final FinishTransactionInteractor finishTransactionInteractor;
    private final ShowTransacaoItemsInteractor showTransacaoItemsInteractor;
    private final FindTransacoesByDateInteractor findTransacoesByDateInteractor;
    private final FindTransacoesByMonthInteractor findTransacoesByMonthInteractor;

    public TransacaoController(
            ProdutoRepository produtoRepository,
            FuncionarioRepository funcionarioRepository,
            ProdutoEntityMapper produtoEntityMapper,
            CreateTransacaoInteractor createTransacaoInteractor,
            ShowAllTransacoesInteractor showAllTransacoesInteractor,
            ShowTransacoesByFuncionarioIdInteractor showTransacoesByFuncionarioIdInteractor,
            ShowOneTransacaoInteractor showOneTransacaoInteractor,
            ShowTransacoesByTypeInteractor showTransacoesByTypeInteractor,
            ShowTransacoesByStatusInteractor showTransacoesByStatusInteractor,
            CreateScheduledTransacaoInteractor createScheduledTransacaoInteractor,
            FinishTransactionInteractor finishTransactionInteractor,
            ShowTransacaoItemsInteractor showTransacaoItemsInteractor,
            FindTransacoesByDateInteractor findTransacoesByDateInteractor,
            FindTransacoesByMonthInteractor findTransacoesByMonthInteractor
    ) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.produtoEntityMapper = produtoEntityMapper;
        this.createTransacaoInteractor = createTransacaoInteractor;
        this.showAllTransacoesInteractor = showAllTransacoesInteractor;
        this.showTransacoesByFuncionarioIdInteractor = showTransacoesByFuncionarioIdInteractor;
        this.showOneTransacaoInteractor = showOneTransacaoInteractor;
        this.showTransacoesByTypeInteractor = showTransacoesByTypeInteractor;
        this.showTransacoesByStatusInteractor = showTransacoesByStatusInteractor;
        this.createScheduledTransacaoInteractor = createScheduledTransacaoInteractor;
        this.finishTransactionInteractor = finishTransactionInteractor;
        this.showTransacaoItemsInteractor = showTransacaoItemsInteractor;
        this.findTransacoesByDateInteractor = findTransacoesByDateInteractor;
        this.findTransacoesByMonthInteractor = findTransacoesByMonthInteractor;
    }

    @GetMapping
    public ResponseEntity<List<ShowTransacaoResponse>> showAllTransactions() {
        List<ShowTransacaoResponse> transacaoResponses = showAllTransacoesInteractor.mostrarTransacoes()
                .stream().map(transacao -> {
                    FuncionarioEntity funcionarioEntity = funcionarioRepository
                            .findByCpf(transacao.getFuncionario().getCpf())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));

                    return new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), null);
                })
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(transacaoResponses);
    }

    @PostMapping("/procurements")
    public ResponseEntity<String> registerProcurement(@RequestBody @Valid CreateTransacaoRequest createTransacaoRequest) {
        List<Item> itens = requestItemToDomain(createTransacaoRequest.itens());
        /**
         * Adicionar reposicao de estoque de produtos.
         */
        Transacao transacao = createTransacaoInteractor
                .registrarTransacao(itens, createTransacaoRequest.funcionarioId(), TransacaoType.COMPRA);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Compra cadastrada com sucesso. " + transacao.getId());
    }

    @PostMapping("/sales")
    public ResponseEntity<String> registerSale(@RequestBody @Valid CreateTransacaoRequest createTransacaoRequest) {
        List<Item> itens = requestItemToDomain(createTransacaoRequest.itens());
        Transacao transacao = createTransacaoInteractor
                .registrarTransacao(itens, createTransacaoRequest.funcionarioId(), TransacaoType.VENDA);

        // Só sera descontado do estoque quando a venda for FINALIZADA.
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Venda cadastrada com sucesso. " + transacao.getId());
    }

    @GetMapping("/employee/{funcionarioId}")
    public ResponseEntity<List<ShowTransacaoResponse>> findSaleByEmployeeId(@PathVariable("funcionarioId") String id) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));

        List<ShowTransacaoResponse> transacaoResponses = showTransacoesByFuncionarioIdInteractor
                .mostrarTransacoesPorFuncionarioId(id)
                .stream().map(transacao ->
                        new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), produtoEntityMapper))
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(transacaoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTransacaoResponse> showSale(@PathVariable("id") String id) {
        Transacao transacao = showOneTransacaoInteractor.mostrarTransacao(id);
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findByCpf(transacao.getFuncionario().getCpf())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), produtoEntityMapper));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<ShowTransacaoResponse>> findTransactionsByMonth(@PathVariable("month") int month) {
        List<ShowTransacaoResponse> transacaoResponses = findTransacoesByMonthInteractor.findTransactionsByMonth(month)
                .stream().map(transacao -> {
                    FuncionarioEntity funcionarioEntity = funcionarioRepository
                            .findByCpf(transacao.getFuncionario().getCpf())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionário não encontrado"));

                    return new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), null);
                })
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(transacaoResponses);
    }

    @GetMapping("/date/{month}/{day}")
    public ResponseEntity<List<ShowTransacaoResponse>> findTransactionsByDate(
            @PathVariable("month") int month,
            @PathVariable("day") int day
    ) {
        List<ShowTransacaoResponse> transacaoResponses = findTransacoesByDateInteractor.findTransacoesByDate(month, day)
                .stream().map(transacao -> {
                    FuncionarioEntity funcionarioEntity = funcionarioRepository
                            .findByCpf(transacao.getFuncionario().getCpf())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));

                    return new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), null);
                })
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(transacaoResponses);
    }

    @PostMapping("/schedule/{type}/{date}")
    public ResponseEntity<String> scheduleTransaction(
            @PathVariable("type") TransacaoType transacaoType,
            @FutureOrPresent(message = "Deve ser uma data no futuro.")
            @PathVariable("date") LocalDateTime dateString,
            @Valid @RequestBody CreateTransacaoRequest createTransacaoRequest
    ) {
        createScheduledTransacaoInteractor.scheduledTransaction(
                requestItemToDomain(createTransacaoRequest.itens()),
                createTransacaoRequest.funcionarioId(),
                transacaoType,
                dateString.toString()
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body("Transação agendada com sucesso.");
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ShowItemResponse>> showTransactionItems(@PathVariable("id") String id) {
        List<ShowItemResponse> items = showTransacaoItemsInteractor.showTransactionItems(id)
                .stream().map(item -> {
                    ProdutoEntity produtoEntity = produtoRepository
                            .findByCodigo(item.getProduto().getCodigo())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

                    return new ShowItemResponse(item.getQuantidade(), produtoEntity);
                }).collect(Collectors.toUnmodifiableList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(items);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ShowTransacaoResponse>> findTransactionsByType(@PathVariable("type") TransacaoType transacaoType) {
        List<ShowTransacaoResponse> transacaoResponses = showTransacoesByTypeInteractor
                .mostrarTransacoesPorTipo(transacaoType)
                .stream().map(transacao -> {
                    FuncionarioEntity funcionarioEntity = funcionarioRepository
                            .findByCpf(transacao.getFuncionario().getCpf()).get();

                    return new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), produtoEntityMapper);
                })
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transacaoResponses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ShowTransacaoResponse>> findTransactionsByStatus(@PathVariable("status") TransacaoStatus transacaoStatus) {
        List<ShowTransacaoResponse> transacaoResponses = showTransacoesByStatusInteractor
                .showTransacoesByStatus(transacaoStatus)
                .stream().map(transacao -> {
                    FuncionarioEntity funcionarioEntity = funcionarioRepository
                            .findByCpf(transacao.getFuncionario().getCpf()).get();

                    return new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), produtoEntityMapper);
                })
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(transacaoResponses);
    }

    @PutMapping("/finalize/{id}")
    public ResponseEntity<String> finalizeTransaction(@PathVariable("id") String id) {
        finishTransactionInteractor.finalizeTransaction(id);
        return ResponseEntity.status(HttpStatus.OK).body("Transação finalizada com sucesso.");
    }

    @GetMapping("/statuses")
    public ResponseEntity<TransacaoStatus[]> showStatuses() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(TransacaoStatus.values());
    }

    @GetMapping("/types")
    public ResponseEntity<TransacaoType[]> showTypes() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(TransacaoType.values());
    }

    private List<Item> requestItemToDomain(List<CreateItemRequest> itemsRequest) {
        return itemsRequest.stream().map(item -> {
                    ProdutoEntity produtoEntity = produtoRepository.findById(item.produtoId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

                    return new Item(item.quantidade(), produtoEntityMapper.toDomain(produtoEntity));
                })
                .collect(Collectors.toUnmodifiableList());
    }
}
