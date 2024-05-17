package br.com.lgmanagement.lgManagement.infra.controller.funcionario;

import br.com.lgmanagement.lgManagement.application.usecases.funcionario.*;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.FuncionarioBuilder;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.request.CreateFuncionarioRequest;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.request.UpdateFuncionarioRequest;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.response.ShowFuncionarioResponse;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class FuncionarioController {

    private final FuncionarioDtoMapper funcionarioDtoMapper;
    private final CreateFuncionarioInteractor createFuncionarioInteractor;
    private final ShowAllFuncionariosInteractor showAllFuncionariosInteractor;
    private final UpdateFuncionarioInteractor updateFuncionarioInteractor;
    private final DeleteFuncionarioInteractor deleteFuncionarioInteractor;
    private final ShowOneFuncionarioInteractor showOneFuncionarioInteractor;

    public FuncionarioController(
            FuncionarioDtoMapper funcionarioDtoMapper,
            CreateFuncionarioInteractor createFuncionarioInteractor,
            ShowAllFuncionariosInteractor showAllFuncionariosInteractor,
            UpdateFuncionarioInteractor updateFuncionarioInteractor,
            DeleteFuncionarioInteractor deleteFuncionarioInteractor,
            ShowOneFuncionarioInteractor showOneFuncionarioInteractor
    ) {
        this.funcionarioDtoMapper = funcionarioDtoMapper;
        this.createFuncionarioInteractor = createFuncionarioInteractor;
        this.showAllFuncionariosInteractor = showAllFuncionariosInteractor;
        this.updateFuncionarioInteractor = updateFuncionarioInteractor;
        this.deleteFuncionarioInteractor = deleteFuncionarioInteractor;
        this.showOneFuncionarioInteractor = showOneFuncionarioInteractor;
    }

    @PostMapping
    public ResponseEntity<String> registerEmployee(
            @RequestBody @Valid CreateFuncionarioRequest createFuncionarioRequest
    ) {
        createFuncionarioInteractor.registrarFuncionario(
                funcionarioDtoMapper.toDomain(createFuncionarioRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Funcionário registrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<List<ShowFuncionarioResponse>> showAllEmployees() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(showAllFuncionariosInteractor.listarFuncionarios()
                        .stream().map(funcionario -> funcionarioDtoMapper.toResponse(funcionario))
                        .collect(Collectors.toUnmodifiableList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowFuncionarioResponse> updateEmployee(
            @PathVariable("id") String id,
            @RequestBody UpdateFuncionarioRequest updateFuncionarioRequest
    ) {
        FuncionarioBuilder funcBuilder = new FuncionarioBuilder();
        Funcionario funcionario = funcBuilder
                .withNomeAndCPF(updateFuncionarioRequest.nome(), updateFuncionarioRequest.cpf());
        if (updateFuncionarioRequest.enderecoDto() != null) {
            funcBuilder.includeEndereco(
                    updateFuncionarioRequest.enderecoDto().rua(),
                    updateFuncionarioRequest.enderecoDto().bairro(),
                    updateFuncionarioRequest.enderecoDto().numero(),
                    updateFuncionarioRequest.enderecoDto().complemento()
            );
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(funcionarioDtoMapper
                        .toResponse(updateFuncionarioInteractor.updateFuncionario(id, funcionario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {
        deleteFuncionarioInteractor.deletarFuncionario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Funcionário deletado com sucesso.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowFuncionarioResponse> showEmployee(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(funcionarioDtoMapper
                        .toResponse(showOneFuncionarioInteractor.mostrarFuncionario(id))
                );
    }
}
