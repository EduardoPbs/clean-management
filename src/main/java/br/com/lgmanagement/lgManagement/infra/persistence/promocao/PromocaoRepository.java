package br.com.lgmanagement.lgManagement.infra.persistence.promocao;

import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromocaoRepository extends JpaRepository<PromocaoEntity, String> {

    List<PromocaoEntity> findByProdutoEntityAndAtivo(ProdutoEntity produtoEntity, Boolean ativo);

    List<PromocaoEntity> findByProdutoEntity(ProdutoEntity produtoEntity);
}
