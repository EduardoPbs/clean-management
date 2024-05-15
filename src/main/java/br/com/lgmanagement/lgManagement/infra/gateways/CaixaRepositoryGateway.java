package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.gateways.caixa.CaixaGateway;
import br.com.lgmanagement.lgManagement.domain.entities.caixa.Caixa;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.caixa.CaixaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CaixaRepositoryGateway implements CaixaGateway {

    private final CaixaRepository caixaRepository;
    private final CaixaEntityMapper caixaEntityMapper;

    public CaixaRepositoryGateway(
            CaixaRepository caixaRepository,
            CaixaEntityMapper caixaEntityMapper
    ) {
        this.caixaRepository = caixaRepository;
        this.caixaEntityMapper = caixaEntityMapper;
    }

    @Override
    public Caixa showCaixa() {
        CaixaEntity caixaEntity = caixaRepository.findTopByOrderByUpdatedAtDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao encontrar dados."));
        return caixaEntityMapper.toDomain(caixaEntity);
    }

    @Override
    @Transactional
    public Boolean openCaixa(BigDecimal valorAbertura) {
        CaixaEntity caixaEntity = caixaRepository.findTopByOrderByUpdatedAtDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao encontrar dados."));
        if (caixaEntity.getFechamento() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um caixa aberto.");
        }

        caixaRepository.save(new CaixaEntity(valorAbertura));
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean closeCaixa() {
        CaixaEntity caixaEntity = caixaRepository.findTopByOrderByUpdatedAtDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao encontrar dados."));

        if (caixaEntity.getFechamento() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O caixa atual já está fechado.");
        }

        caixaEntity.setValorFechamento(caixaEntity.getValorAtual());
        caixaEntity.setFechamento(LocalDateTime.now());
        caixaRepository.save(caixaEntity);
        return Boolean.TRUE;
    }

    @Override
    public List<Caixa> showAllCaixas() {
        List<Caixa> caixas = caixaRepository.findAll()
                .stream().map(caixaEntity -> caixaEntityMapper.toDomain(caixaEntity))
                .collect(Collectors.toUnmodifiableList());
        return caixas;
    }

    @Override
    public List<Caixa> findCaixaByMonth(int month) {
        if (month < 1 || month > 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida.");
        }

        List<Caixa> caixas = caixaRepository.findByMonth(month).stream()
                .map(caixaEntity -> caixaEntityMapper.toDomain(caixaEntity))
                .collect(Collectors.toUnmodifiableList());
        return caixas;
    }

    @Override
    public List<Caixa> findCaixaByDate(int month, int day) {
        if (day < 1 || day > 31 && month < 1 || month > 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida.");
        }

        List<Caixa> caixas = caixaRepository.findByDate(month, day).stream()
                .map(caixaEntity -> caixaEntityMapper.toDomain(caixaEntity))
                .collect(Collectors.toUnmodifiableList());
        return caixas;
    }
}
