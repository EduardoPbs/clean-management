package br.com.lgmanagement.lgManagement.infra.persistence.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    Optional<ProdutoEntity> findByCodigo(String codigo);

    List<ProdutoEntity> findAllByAtivoTrue();

    @Query(nativeQuery = true, value = "SELECT * FROM produtos WHERE estoque <= 10")
    List<ProdutoEntity> findByLowStock();
}
