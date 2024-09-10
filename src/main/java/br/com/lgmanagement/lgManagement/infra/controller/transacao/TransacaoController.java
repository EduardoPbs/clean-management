package br.com.lgmanagement.lgManagement.infra.controller.transacao;

import br.com.lgmanagement.lgManagement.application.facades.transacoes.ShowTransactionsFacade;
import br.com.lgmanagement.lgManagement.application.facades.transacoes.CreateTransactionFacade;
import br.com.lgmanagement.lgManagement.application.usecases.transacao.*;
import br.com.lgmanagement.lgManagement.domain.entities.PagamentoType;
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
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransacaoController {

    private final ProdutoRepository produtoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoEntityMapper produtoEntityMapper;
    private final CreateTransactionFacade createTransactionFacade;
    private final ShowTransactionsFacade showTransactionsFacade;
    private final FinishTransactionInteractor finishTransactionInteractor;
    private final ShowTransacaoItemsInteractor showTransacaoItemsInteractor;

    public TransacaoController(
            ProdutoRepository produtoRepository,
            FuncionarioRepository funcionarioRepository,
            CreateTransactionFacade createTransactionFacade,
            ShowTransactionsFacade showTransactionsFacade,
            ProdutoEntityMapper produtoEntityMapper,
            FinishTransactionInteractor finishTransactionInteractor,
            ShowTransacaoItemsInteractor showTransacaoItemsInteractor
    ) {
        this.produtoRepository = produtoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.createTransactionFacade = createTransactionFacade;
        this.showTransactionsFacade = showTransactionsFacade;
        this.produtoEntityMapper = produtoEntityMapper;
        this.finishTransactionInteractor = finishTransactionInteractor;
        this.showTransacaoItemsInteractor = showTransacaoItemsInteractor;
    }

    @GetMapping
    public ResponseEntity<List<ShowTransacaoResponse>> showAllTransactions() {
        List<ShowTransacaoResponse> transacaoResponses = showTransactionsFacade.all()
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
        Transacao transacao = createTransactionFacade
                .create(itens, createTransacaoRequest.funcionarioId(), PagamentoType.NAO_DEFINIDO, TransacaoType.COMPRA);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Compra cadastrada com sucesso. " + transacao.getId());
    }

    @PostMapping("/sales")
    public ResponseEntity<String> registerSale(@RequestBody @Valid CreateTransacaoRequest createTransacaoRequest) {
        List<Item> itens = requestItemToDomain(createTransacaoRequest.itens());
        Transacao transacao = createTransactionFacade
                .create(
                        itens,
                        createTransacaoRequest.funcionarioId(),
                        createTransacaoRequest.pagamentoType(),
                        TransacaoType.VENDA
                );

        // Só sera descontado do estoque quando a venda for FINALIZADA.
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Venda cadastrada com sucesso. " + transacao.getId());
    }

    @GetMapping("/employee/{funcionarioId}")
    public ResponseEntity<List<ShowTransacaoResponse>> findSaleByEmployeeId(@PathVariable("funcionarioId") String id) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));

        List<ShowTransacaoResponse> transacaoResponses = showTransactionsFacade
                .allByFuncionarioId(id)
                .stream().map(transacao ->
                        new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), produtoEntityMapper))
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(transacaoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTransacaoResponse> showSale(@PathVariable("id") String id) {
        Transacao transacao = showTransactionsFacade.viewById(id);
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findByCpf(transacao.getFuncionario().getCpf())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ShowTransacaoResponse(transacao, funcionarioEntity.getId(), produtoEntityMapper));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<ShowTransacaoResponse>> findTransactionsByMonth(@PathVariable("month") int month) {
        List<ShowTransacaoResponse> transacaoResponses = showTransactionsFacade.allByMonth(month)
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
        List<ShowTransacaoResponse> transacaoResponses = showTransactionsFacade.allByDate(month, day)
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
        createTransactionFacade.createScheduled(
                requestItemToDomain(createTransacaoRequest.itens()),
                createTransacaoRequest.funcionarioId(),
                transacaoType,
                createTransacaoRequest.pagamentoType(),
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

                    return new ShowItemResponse(item.getQuantidade(), produtoEntity, item.getValorUnitario());
                }).collect(Collectors.toUnmodifiableList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(items);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ShowTransacaoResponse>> findTransactionsByType(@PathVariable("type") TransacaoType transacaoType) {
        List<ShowTransacaoResponse> transacaoResponses = showTransactionsFacade
                .allByType(transacaoType).stream()
                .sorted(Comparator.comparing(Transacao::getCreatedAt))
                .map(transacao -> {
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
        List<ShowTransacaoResponse> transacaoResponses = showTransactionsFacade
                .allByStatus(transacaoStatus)
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

    @GetMapping("/payment-types")
    public ResponseEntity<PagamentoType[]> showPaymentTypes() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(PagamentoType.values());
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
