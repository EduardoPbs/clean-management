package br.com.lgmanagement.lgManagement.infra.persistence.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {
    List<ItemEntity> findByTransacaoId(String id);
}
