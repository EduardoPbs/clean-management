package br.com.lgmanagement.lgManagement.infra.persistence.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, String> {
    Optional<FuncionarioEntity> findByCpf(String cpf);
}
