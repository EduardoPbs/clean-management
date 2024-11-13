package br.com.lgmanagement.lgManagement.infra.persistence.estoque_minimo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueMinimoRepository extends JpaRepository<EstoqueMinimoEntity, String> {
    Optional<EstoqueMinimoEntity> findByProdutoEntityId(String id);
}
