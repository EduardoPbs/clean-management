package br.com.lgmanagement.lgManagement.infra.controller.caixa;

import br.com.lgmanagement.lgManagement.application.usecases.caixa.*;
import br.com.lgmanagement.lgManagement.application.usecases.movimentacao.*;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.infra.controller.caixa.request.OpenCashierRequest;
import br.com.lgmanagement.lgManagement.infra.controller.caixa.request.RegisterMovementRequest;
import br.com.lgmanagement.lgManagement.infra.controller.caixa.response.ShowCaixaResponse;
import br.com.lgmanagement.lgManagement.infra.controller.caixa.response.ShowMovementResponse;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntityMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashier")
public class CaixaController {

    private final ShowCaixaInteractor showCaixaInteractor;
    private final CreateMovementInteractor createMovementInteractor;
    private final OpenCaixaInteractor openCaixaInteractor;
    private final CloseCaixaInteractor closeCaixaInteractor;
    private final ShowAllMovementsInteractor showAllMovementsInteractor;
    private final ShowAllCaixasInteractor showAllCaixasInteractor;
    private final FindCaixaByMonthInteractor findCaixaByMonthInteractor;
    private final FindCaixaByDateInteractor findCaixaByDateInteractor;
    private final FindMovementsByMonthInteractor findMovementsByMonthInteractor;
    private final FindMovementsByDateInteractor findMovementsByDateInteractor;
    private final CaixaEntityMapper caixaEntityMapper;

    public CaixaController(
            ShowCaixaInteractor showCaixaInteractor,
            CreateMovementInteractor createMovementInteractor,
            OpenCaixaInteractor openCaixaInteractor,
            CloseCaixaInteractor closeCaixaInteractor,
            ShowAllMovementsInteractor showAllMovementsInteractor,
            ShowAllCaixasInteractor showAllCaixasInteractor,
            FindCaixaByMonthInteractor findCaixaByMonthInteractor,
            FindCaixaByDateInteractor findCaixaByDateInteractor,
            FindMovementsByMonthInteractor findMovementsByMonthInteractor,
            FindMovementsByDateInteractor findMovementsByDateInteractor,
            CaixaEntityMapper caixaEntityMapper
    ) {
        this.showCaixaInteractor = showCaixaInteractor;
        this.createMovementInteractor = createMovementInteractor;
        this.openCaixaInteractor = openCaixaInteractor;
        this.closeCaixaInteractor = closeCaixaInteractor;
        this.showAllMovementsInteractor = showAllMovementsInteractor;
        this.showAllCaixasInteractor = showAllCaixasInteractor;
        this.findCaixaByMonthInteractor = findCaixaByMonthInteractor;
        this.findCaixaByDateInteractor = findCaixaByDateInteractor;
        this.findMovementsByMonthInteractor = findMovementsByMonthInteractor;
        this.findMovementsByDateInteractor = findMovementsByDateInteractor;
        this.caixaEntityMapper = caixaEntityMapper;
    }

    @GetMapping("/current")
    public ResponseEntity<ShowCaixaResponse> showCurrentCashier() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ShowCaixaResponse(showCaixaInteractor.showCaixa(), caixaEntityMapper));
    }

    @GetMapping("/month/{month}")
    public ResponseEntity<List<ShowCaixaResponse>> findCashiersByMonth(@PathVariable("month") int month) {
        List<ShowCaixaResponse> caixaResponses = findCaixaByMonthInteractor.findCaixaByMonth(month)
                .stream().map(caixa -> new ShowCaixaResponse(caixa, caixaEntityMapper))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(caixaResponses);
    }

    @GetMapping("/date/{month}-{day}")
    public ResponseEntity<List<ShowCaixaResponse>> findCashiersByDate(
            @PathVariable("month") int month,
            @PathVariable("day") int day
    ) {
        List<ShowCaixaResponse> caixaResponses = findCaixaByDateInteractor.findCaixaByDate(month, day)
                .stream().map(caixa -> new ShowCaixaResponse(caixa, caixaEntityMapper))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(caixaResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShowCaixaResponse>> showAllCashiers() {
        List<ShowCaixaResponse> caixaResponses = showAllCaixasInteractor.showAllCaixas()
                .stream().map(caixa -> new ShowCaixaResponse(caixa, caixaEntityMapper))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(caixaResponses);
    }

    @GetMapping("/movements/all")
    public ResponseEntity<List<ShowMovementResponse>> showAllMovements() {
        List<ShowMovementResponse> movementResponses = showAllMovementsInteractor.showAllMovements()
                .stream().map(movimentacao -> new ShowMovementResponse(movimentacao.getValor(), movimentacao.getTransacaoType(), movimentacao.getCreatedAt()))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(movementResponses);
    }

    @GetMapping("/movements/month/{month}")
    public ResponseEntity<List<ShowMovementResponse>> findAllMovementsByMonth(@PathVariable("month") int month) {
        List<ShowMovementResponse> movementResponses = findMovementsByMonthInteractor.findMovementsByMonth(month)
                .stream().map(movimentacao -> new ShowMovementResponse(movimentacao.getValor(), movimentacao.getTransacaoType(), movimentacao.getCreatedAt()))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(movementResponses);
    }

    @GetMapping("/movements/date/{month}-{day}")
    public ResponseEntity<List<ShowMovementResponse>> findAllMovementsByMonth(
            @PathVariable("month") int month,
            @PathVariable("day") int day
    ) {
        List<ShowMovementResponse> movementResponses = findMovementsByDateInteractor.findMovementsByDate(month, day)
                .stream()
                .map(movimentacao -> new ShowMovementResponse(movimentacao.getValor(), movimentacao.getTransacaoType(), movimentacao.getCreatedAt()))
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(movementResponses);
    }


    @PostMapping("/movement/{type}")
    public ResponseEntity<String> registerMovement(
            @RequestBody @Valid RegisterMovementRequest registerMovementRequest,
            @PathVariable("type") TransacaoType transacaoType
    ) {
        createMovementInteractor.registerMovement(
                registerMovementRequest.valor(),
                showCaixaInteractor.showCaixa(),
                transacaoType
        );

        return ResponseEntity.status(HttpStatus.OK)
                .body("Movimentação cadastrada com sucesso.");
    }

    @PutMapping("/open")
    public ResponseEntity<Boolean> openCashier(@RequestBody @Valid OpenCashierRequest openCashierRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(openCaixaInteractor.openCaixa(openCashierRequest.valorAbertura()));
    }

    @PutMapping("/close")
    public ResponseEntity<Boolean> closeCashier() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(closeCaixaInteractor.closeCaixa());
    }
}
