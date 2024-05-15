package br.com.lgmanagement.lgManagement.infra.controller.produto;

import br.com.lgmanagement.lgManagement.application.usecases.produto.*;
import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;
import br.com.lgmanagement.lgManagement.domain.entities.produto.ProdutoBuilder;
import br.com.lgmanagement.lgManagement.infra.controller.produto.request.CreateProdutoRequest;
import br.com.lgmanagement.lgManagement.infra.controller.produto.request.UpdateProdutoRequest;
import br.com.lgmanagement.lgManagement.infra.controller.produto.response.ShowProdutoResponse;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/commodities")
public class ProdutoController {

    private final ProdutoEntityMapper produtoEntityMapper;
    private final CreateProdutoInteractor createProdutoInteractor;
    private final ShowAllProdutosInteractor showAllProdutosInteractor;
    private final UpdateProdutoInteractor updateProdutoInteractor;
    private final ShowOneProdutoInteractor showOneProdutoInteractor;
    private final DeleteProdutoInteractor deleteProdutoInteractor;
    private final RestoreProdutoInteractor restoreProdutoInteractor;
    private final ShowProdutoWithLowStockInteractor showProdutoWithLowStockInteractor;
    private final ShowAllProdutosAtivosInteractor showAllProdutosAtivosInteractor;

    public ProdutoController(
            ProdutoEntityMapper produtoEntityMapper,
            CreateProdutoInteractor createProdutoInteractor,
            ShowAllProdutosInteractor showAllProdutosInteractor,
            UpdateProdutoInteractor updateProdutoInteractor,
            ShowOneProdutoInteractor showOneProdutoInteractor,
            DeleteProdutoInteractor deleteProdutoInteractor,
            RestoreProdutoInteractor restoreProdutoInteractor,
            ShowProdutoWithLowStockInteractor showProdutoWithLowStockInteractor,
            ShowAllProdutosAtivosInteractor showAllProdutosAtivosInteractor
    ) {
        this.produtoEntityMapper = produtoEntityMapper;
        this.createProdutoInteractor = createProdutoInteractor;
        this.showAllProdutosInteractor = showAllProdutosInteractor;
        this.updateProdutoInteractor = updateProdutoInteractor;
        this.showOneProdutoInteractor = showOneProdutoInteractor;
        this.deleteProdutoInteractor = deleteProdutoInteractor;
        this.restoreProdutoInteractor = restoreProdutoInteractor;
        this.showProdutoWithLowStockInteractor = showProdutoWithLowStockInteractor;
        this.showAllProdutosAtivosInteractor = showAllProdutosAtivosInteractor;
    }

    @PostMapping
    public ResponseEntity<String> createCommodity(@RequestBody @Valid CreateProdutoRequest createProdutoRequest) {
        createProdutoInteractor.createProduto(
                produtoEntityMapper.toDomain(createProdutoRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto criado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<ShowProdutoResponse>> showAllCommodities() {
        List<ProdutoEntity> produtoEntities = showAllProdutosInteractor
                .showAllProdutos().stream()
                .map(mercadoria -> produtoEntityMapper.toEntity(mercadoria))
                .collect(Collectors.toUnmodifiableList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoEntities.stream().map(
                        produtoEntity -> new ShowProdutoResponse(produtoEntity)).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowProdutoResponse> updateCommodity(
            @PathVariable("id") String id,
            @RequestBody @Valid UpdateProdutoRequest updateProdutoRequest
    ) {
        ProdutoBuilder produtoBuilder = new ProdutoBuilder();
        Produto produto = produtoBuilder.withNomeAndCodigo(
                updateProdutoRequest.nome(),
                updateProdutoRequest.codigo()
        );
        produtoBuilder.includeValor(updateProdutoRequest.valor());
        produtoBuilder.includeCategorias(updateProdutoRequest.categorias());
        produtoBuilder.includeEstoque(updateProdutoRequest.estoque());

        ProdutoEntity produtoEntity = produtoEntityMapper.toEntity(
                updateProdutoInteractor.updateProduto(id, produto));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ShowProdutoResponse(produtoEntity));
    }

    @PatchMapping("/restore/{id}")
    public ResponseEntity restoreCommodity(@PathVariable("id") String id) {
        restoreProdutoInteractor.activeProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommodity(@PathVariable("id") String id) {
        deleteProdutoInteractor.deleteProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Produto deletado com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowProdutoResponse> showCommodity(@PathVariable("id") String id) {
        ProdutoEntity produtoEntity = produtoEntityMapper
                .toEntity(showOneProdutoInteractor.showProduto(id));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ShowProdutoResponse(produtoEntity));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ShowProdutoResponse>> showProductsWithLowStock() {
        List<ProdutoEntity> showProdutoResponses = showProdutoWithLowStockInteractor
                .showProdutosWithLowStock().stream()
                .map(produto -> produtoEntityMapper.toEntity(produto))
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(showProdutoResponses.stream().map(
                        produtoEntity -> new ShowProdutoResponse(produtoEntity)).toList());
    }

    @GetMapping("actives")
    public ResponseEntity<List<ShowProdutoResponse>> showAllActiveProducts() {
        List<ProdutoEntity> showProdutoResponses = showAllProdutosAtivosInteractor
                .showAllProdutosAtivos().stream()
                .map(produto -> produtoEntityMapper.toEntity(produto))
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(showProdutoResponses.stream().map(
                        produtoEntity -> new ShowProdutoResponse(produtoEntity)).toList());
    }

    @GetMapping("/categories")
    public ResponseEntity<Categoria[]> showCategories() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Categoria.values());
    }
}
