package br.com.lgmanagement.lgManagement.infra.persistence.transacao;

import br.com.lgmanagement.lgManagement.domain.entities.TransacaoStatus;
import br.com.lgmanagement.lgManagement.domain.entities.TransacaoType;
import br.com.lgmanagement.lgManagement.infra.persistence.funcionario.FuncionarioEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, String> {

    List<TransacaoEntity> findByfuncionarioEntity(FuncionarioEntity funcionarioEntity);

    List<TransacaoEntity> findByTransacaoType(TransacaoType transacaoType);

    List<TransacaoEntity> findByTransacaoStatus(TransacaoStatus transacaoStatus);

    @Query(nativeQuery = true, value = "SELECT * FROM transacoes WHERE is_scheduled = true AND transacao_status != 'FINALIZADO';")
    List<TransacaoEntity> findByIsScheduledTrue();

    @Query("""
                SELECT t FROM TransacaoEntity t
                WHERE MONTH(t.createdAt) = :mes
            """)
    List<TransacaoEntity> findTransacoesByMonth(@Param("mes") int mes);

    @Query("""
            SELECT t FROM TransacaoEntity t 
            WHERE MONTH(t.createdAt) = :mes
            AND DAY(t.createdAt) = :dia
            """)
    List<TransacaoEntity> findTransacoesByDate(@Param("mes") int mes, @Param("dia") int dia);
}
