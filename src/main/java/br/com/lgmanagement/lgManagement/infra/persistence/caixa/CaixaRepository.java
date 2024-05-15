package br.com.lgmanagement.lgManagement.infra.persistence.caixa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CaixaRepository extends JpaRepository<CaixaEntity, String> {

    Optional<CaixaEntity> findTopByOrderByUpdatedAtDesc();

    Optional<CaixaEntity> findByAbertura(LocalDateTime abertura);

    @Query("SELECT c FROM CaixaEntity c WHERE MONTH(c.abertura) = :mes")
    List<CaixaEntity> findByMonth(@Param("mes") int mes);

    @Query("""
            SELECT c FROM CaixaEntity c 
            WHERE MONTH(c.abertura) = :mes 
            AND DAY(c.abertura) = :dia
            """)
    List<CaixaEntity> findByDate(@Param("mes") int mes, @Param("dia") int dia);
}
