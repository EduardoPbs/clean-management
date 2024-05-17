package br.com.lgmanagement.lgManagement.infra.persistence.funcionario;

import br.com.lgmanagement.lgManagement.domain.entities.usuario.Usuario;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.request.CreateFuncionarioRequest;
import br.com.lgmanagement.lgManagement.domain.entities.Endereco;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.request.UpdateFuncionarioRequest;
import br.com.lgmanagement.lgManagement.infra.controller.funcionario.response.ShowFuncionarioResponse;

public class FuncionarioDtoMapper {

    private final FuncionarioEntityMapper funcionarioEntityMapper;

    public FuncionarioDtoMapper(
            FuncionarioEntityMapper funcionarioEntityMapper
    ) {
        this.funcionarioEntityMapper = funcionarioEntityMapper;
    }

    public ShowFuncionarioResponse toResponse(Funcionario funcionario) {
        return new ShowFuncionarioResponse(funcionarioEntityMapper.toEntity(funcionario));
    }

    public Funcionario toDomain(CreateFuncionarioRequest createFuncionarioRequest) {
        return new Funcionario(
                createFuncionarioRequest.nome(),
                createFuncionarioRequest.cpf(),
                new Endereco(
                        createFuncionarioRequest.endereco().rua(),
                        createFuncionarioRequest.endereco().bairro(),
                        createFuncionarioRequest.endereco().numero(),
                        createFuncionarioRequest.endereco().complemento()
                ),
                new Usuario(
                        createFuncionarioRequest.usuarioRequestDto().email(),
                        createFuncionarioRequest.usuarioRequestDto().password()
                )
        );
    }

    public Funcionario toDomain(UpdateFuncionarioRequest updateFuncionarioRequest) {
        Funcionario funcionario = new Funcionario();

        if (updateFuncionarioRequest.nome() != null) {
            funcionario.setNome(updateFuncionarioRequest.nome());
        }

        if (updateFuncionarioRequest.cpf() != null) {
            funcionario.setCpf(updateFuncionarioRequest.cpf());
        }

        if (updateFuncionarioRequest.enderecoDto().rua() != null) {
            funcionario.getEndereco().setRua(updateFuncionarioRequest.enderecoDto().rua());
        }

        if (updateFuncionarioRequest.enderecoDto().bairro() != null) {
            funcionario.getEndereco().setBairro(updateFuncionarioRequest.enderecoDto().bairro());
        }

        if (updateFuncionarioRequest.enderecoDto().numero() != null) {
            funcionario.getEndereco().setNumero(updateFuncionarioRequest.enderecoDto().numero());
        }

        if (updateFuncionarioRequest.enderecoDto().complemento() != null) {
            funcionario.getEndereco().setComplemento(updateFuncionarioRequest.enderecoDto().complemento());
        }

        if (updateFuncionarioRequest.atualizarUsuarioRequestDto().email() != null) {
            funcionario.getUsuario().setEmail(updateFuncionarioRequest.atualizarUsuarioRequestDto().email());
        }

        if (updateFuncionarioRequest.atualizarUsuarioRequestDto().password() != null) {
            funcionario.getUsuario().setPassword(updateFuncionarioRequest.atualizarUsuarioRequestDto().password());
        }

        /**
         * Implement Venda update (if necessary)
         */
        return funcionario;
    }
}