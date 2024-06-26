package br.com.lgmanagement.lgManagement.infra.controller.promocao;

import br.com.lgmanagement.lgManagement.application.usecases.promocao.*;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;
import br.com.lgmanagement.lgManagement.infra.controller.promocao.request.CreatePromocaoRequest;
import br.com.lgmanagement.lgManagement.infra.controller.promocao.response.ShowPromocaoResponse;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromocaoController {

    private final ProdutoEntityMapper produtoEntityMapper;
    private final CreatePromotionInteractor createPromotionInteractor;
    private final ShowAllPromotionsInteractor showAllPromotionsInteractor;
    private final FindPromotionsByProductInteractor findPromotionsByProductInteractor;
    private final ActivePromotionInteractor activePromotionInteractor;
    private final DisablePromotionInteractor disablePromotionInteractor;
    private final ShowPromotionByIdInteractor showPromotionByIdInteractor;

    public PromocaoController(
            ProdutoEntityMapper produtoEntityMapper,
            CreatePromotionInteractor createPromotionInteractor,
            ShowAllPromotionsInteractor showAllPromotionsInteractor,
            FindPromotionsByProductInteractor findPromotionsByProductInteractor,
            ActivePromotionInteractor activePromotionInteractor,
            DisablePromotionInteractor disablePromotionInteractor,
            ShowPromotionByIdInteractor showPromotionByIdInteractor
    ) {
        this.produtoEntityMapper = produtoEntityMapper;
        this.createPromotionInteractor = createPromotionInteractor;
        this.showAllPromotionsInteractor = showAllPromotionsInteractor;
        this.findPromotionsByProductInteractor = findPromotionsByProductInteractor;
        this.activePromotionInteractor = activePromotionInteractor;
        this.disablePromotionInteractor = disablePromotionInteractor;
        this.showPromotionByIdInteractor = showPromotionByIdInteractor;
    }

    @PostMapping("/create/{produtoId}")
    public ResponseEntity<String> createPromotion(
            @PathVariable("produtoId") String produtoId,
            @RequestBody @Valid CreatePromocaoRequest createPromocaoRequest
    ) {
        createPromotionInteractor.createPromotion(
                produtoId,
                createPromocaoRequest.pocentagemDesconto(),
                createPromocaoRequest.dataInicio(),
                createPromocaoRequest.dataFim()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Promoção criada com succeso. " +
                        " Início em: " + createPromocaoRequest.dataInicio() +
                        ". Fim: " + createPromocaoRequest.dataFim()
                );
    }

    @GetMapping
    public ResponseEntity<List<ShowPromocaoResponse>> showAllPromotions() {
        List<ShowPromocaoResponse> showPromocaoResponses = showAllPromotionsInteractor.showAllPromotions()
                .stream().map(promocao -> new ShowPromocaoResponse(promocao, produtoEntityMapper))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(showPromocaoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowPromocaoResponse> showPromotionById(@PathVariable("id") String id) {
        Promocao promotion = showPromotionByIdInteractor.showPromotionById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ShowPromocaoResponse(promotion, produtoEntityMapper));
    }

    @GetMapping("/product/{produtoId}")
    public ResponseEntity<List<ShowPromocaoResponse>> findPromotionsByProduct(@PathVariable("produtoId") String produtoId) {
        List<ShowPromocaoResponse> showPromocaoResponses = findPromotionsByProductInteractor.showPromotionsByProduct(produtoId)
                .stream().map(promocao -> new ShowPromocaoResponse(promocao, produtoEntityMapper))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(showPromocaoResponses);
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<String> activePromotion(@PathVariable("id") String id) {
        activePromotionInteractor.activePromotion(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Promoção ativada com sucesso.");
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<String> disablePromotion(@PathVariable("id") String id) {
        disablePromotionInteractor.disablePromotion(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Promoção desativada com sucesso.");
    }
}
