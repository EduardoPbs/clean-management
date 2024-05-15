package br.com.lgmanagement.lgManagement.infra.persistence.movimentacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEntity, String> {

    @Query("SELECT m FROM MovimentacaoEntity m WHERE DAY(m.createdAt) = :dia")
    List<MovimentacaoEntity> findByDay(@Param("dia") int dia);

    @Query("SELECT m FROM MovimentacaoEntity m WHERE MONTH(m.createdAt) = :mes")
    List<MovimentacaoEntity> findByMonth(@Param("mes") int mes);

    @Query("""
            SELECT m FROM MovimentacaoEntity m 
            WHERE MONTH(m.createdAt) = :mes 
            AND DAY(m.createdAt) = :dia
            """)
    List<MovimentacaoEntity> findByDate(@Param("mes") int mes, @Param("dia") int dia);
}
