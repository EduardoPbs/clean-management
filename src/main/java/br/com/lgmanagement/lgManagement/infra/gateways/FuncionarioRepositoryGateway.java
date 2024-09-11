package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.gateways.funcionario.FuncionarioGateway;
import br.com.lgmanagement.lgManagement.domain.entities.funcionario.Funcionario;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FuncionarioRepositoryGateway implements FuncionarioGateway {

    private final FuncionarioRepository funcionarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final TransacaoRepository transacaoRepository;
    private final FuncionarioEntityMapper funcionarioEntityMapper;
    private final UsuarioRepositoryGateway usuarioRepositoryGateway;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public FuncionarioRepositoryGateway(
            FuncionarioRepository funcionarioRepository,
            UsuarioRepository usuarioRepository,
            TransacaoRepository transacaoRepository,
            FuncionarioEntityMapper funcionarioEntityMapper,
            UsuarioRepositoryGateway usuarioRepositoryGateway,
            UsuarioEntityMapper usuarioEntityMapper
    ) {
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.transacaoRepository = transacaoRepository;
        this.funcionarioEntityMapper = funcionarioEntityMapper;
        this.usuarioRepositoryGateway = usuarioRepositoryGateway;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @Override
    @Transactional
    public Funcionario registrarFuncionario(Funcionario funcionario) {
        Optional<FuncionarioEntity> funcByCpf =
                funcionarioRepository.findByCpf(funcionario.getCpf());
        if (funcByCpf.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado no sistema.");
        }

        UserDetails usuarioByEmail =
                usuarioRepository.findByEmail(funcionario.getUsuario().getEmail());
        if (usuarioByEmail != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado no sistema.");
        }

        FuncionarioEntity funcionarioEntity = funcionarioEntityMapper
                .toEntity(funcionario);

        UsuarioEntity usuarioEntity = usuarioEntityMapper
                .toEntity(funcionario.getUsuario());

        final String encryptedPass = usuarioEntity
                .passwordEncoder(usuarioEntity.getPassword());
        usuarioEntity.setPassword(encryptedPass);

        funcionarioEntity.setUsuarioEntity(usuarioEntity);
        usuarioEntity.setFuncionarioEntity(funcionarioEntity);

        funcionarioRepository.save(funcionarioEntity);
        return funcionarioEntityMapper.toDomain(funcionarioEntity);
    }

    @Override
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll().stream()
                .map(func -> funcionarioEntityMapper.toDomain(func))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Funcionario atualizarFuncionario(String id, Funcionario funcionario) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));
        funcionarioEntity.updateFuncionario(
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getEndereco(),
                usuarioEntityMapper.toEntity(funcionario.getUsuario())
        );
        return funcionarioEntityMapper.toDomain(funcionarioRepository.save(funcionarioEntity));
    }

    @Override
    @Transactional
    public void deletarFuncionario(String id) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Funcionário não encontrado."));

        transacaoRepository.updateToDefaultUser(id);
        funcionarioRepository.delete(funcionarioEntity);
        usuarioRepositoryGateway.deletarUsuario(funcionarioEntity.getUsuarioEntity().getId());
    }

    @Override
    public Funcionario mostrarFuncionario(String id) {
        return funcionarioEntityMapper.toDomain(funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Funcionário não encontrado.")));
    }
}
