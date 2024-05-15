package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.application.gateways.funcionario.FuncionarioGateway;
import br.com.lgmanagement.lgManagement.application.usecases.funcionario.*;
import br.com.lgmanagement.lgManagement.infra.gateways.FuncionarioRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.gateways.UsuarioRepositoryGateway;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioDtoMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuncionarioConfig {

    @Bean
    CreateFuncionarioInteractor createFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        return new CreateFuncionarioInteractor(funcionarioGateway);
    }

    @Bean
    ShowAllFuncionariosInteractor showAllFuncionariosInteractor(FuncionarioGateway funcionarioGateway) {
        return new ShowAllFuncionariosInteractor(funcionarioGateway);
    }

    @Bean
    UpdateFuncionarioInteractor updateFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        return new UpdateFuncionarioInteractor(funcionarioGateway);
    }

    @Bean
    DeleteFuncionarioInteractor deleteFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        return new DeleteFuncionarioInteractor(funcionarioGateway);
    }

    @Bean
    ShowOneFuncionarioInteractor showOneFuncionarioInteractor(FuncionarioGateway funcionarioGateway) {
        return new ShowOneFuncionarioInteractor(funcionarioGateway);
    }

    @Bean
    FuncionarioRepositoryGateway funcionarioRepositoryGateway(
            FuncionarioRepository funcionarioRepository,
            UsuarioRepository usuarioRepository,
            FuncionarioEntityMapper funcionarioEntityMapper,
            UsuarioRepositoryGateway usuarioRepositoryGateway,
            UsuarioEntityMapper usuarioEntityMapper
    ) {
        return new FuncionarioRepositoryGateway(
                funcionarioRepository,
                usuarioRepository,
                funcionarioEntityMapper,
                usuarioRepositoryGateway,
                usuarioEntityMapper
        );
    }

    @Bean
    FuncionarioEntityMapper funcionarioEntityMapper(
            UsuarioEntityMapper usuarioEntityMapper,
            FuncionarioRepository funcionarioRepository,
            TransacaoEntityMapper transacaoEntityMapper,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        return new FuncionarioEntityMapper(
                usuarioEntityMapper,
                funcionarioRepository,
                transacaoEntityMapper,
                produtoEntityMapper
        );
    }

    @Bean
    FuncionarioDtoMapper funcionarioDtoMapper(
            FuncionarioEntityMapper funcionarioEntityMapper,
            UsuarioEntityMapper usuarioEntityMapper,
            TransacaoEntityMapper transacaoEntityMapper
    ) {
        return new FuncionarioDtoMapper(
                funcionarioEntityMapper,
                usuarioEntityMapper,
                transacaoEntityMapper
        );
    }
}
