package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.gateways.promocao.PromocaoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.promocao.PromocaoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.promocao.PromocaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.promocao.PromocaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PromocaoRepositoryGateway implements PromocaoGateway {

    private final PromocaoEntityMapper promocaoEntityMapper;
    private final ProdutoRepository produtoRepository;
    private final PromocaoRepository promocaoRepository;

    public PromocaoRepositoryGateway(
            PromocaoEntityMapper promocaoEntityMapper,
            ProdutoRepository produtoRepository,
            PromocaoRepository promocaoRepository
    ) {
        this.promocaoEntityMapper = promocaoEntityMapper;
        this.produtoRepository = produtoRepository;
        this.promocaoRepository = promocaoRepository;
    }

    @Override
    @Transactional
    public Promocao createPromotion(
            String produtoId,
            BigDecimal porcentagemDesconto,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        ProdutoEntity produtoEntity = produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

        List<PromocaoEntity> promocaoEntities = promocaoRepository
                .findByProdutoEntityAndAtivo(produtoEntity, Boolean.TRUE);

        if (!promocaoEntities.isEmpty()) {
            PromocaoEntity promocaoAtiva = promocaoEntities.stream()
                    .max(Comparator.comparing(PromocaoEntity::getDataFim))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao obter promoção ativa."));

            if (dataInicio.isAfter(promocaoAtiva.getDataFim())) {
                PromocaoEntity promocaoEntity = new PromocaoEntity(produtoEntity, porcentagemDesconto, dataInicio, dataFim);
                produtoEntity.applyPromocao(promocaoEntity);
                promocaoRepository.save(promocaoEntity);
                Promocao promocao = promocaoEntityMapper.toDomain(promocaoEntity);
                return promocao;
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "A data de início da nova promoção deve ser posterior à data de término da promoção ativa."
                );
            }
        }
        PromocaoEntity promocaoEntity = new PromocaoEntity(produtoEntity, porcentagemDesconto, dataInicio, dataFim);

        if (promocaoEntity.getDataInicio().isBefore(LocalDateTime.now()) && !promocaoEntity.getAtivo()) {
            promocaoEntity.setAtivo(Boolean.TRUE);
        }

        if (promocaoEntity.getDataFim().isBefore(LocalDateTime.now()) && promocaoEntity.getAtivo()) {
            promocaoEntity.setAtivo(Boolean.FALSE);
        }

        produtoEntity.applyPromocao(promocaoEntity);
        promocaoRepository.save(promocaoEntity);
        Promocao promocao = promocaoEntityMapper.toDomain(promocaoEntity);
        return promocao;
    }

    @Override
    public List<Promocao> showAllPromotions() {
        List<Promocao> promocaoList = promocaoRepository.findAll()
                .stream().map(promocaoEntity -> promocaoEntityMapper.toDomain(promocaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return promocaoList;
    }

    @Override
    public List<Promocao> showPromotionsByProduct(String produtoId) {
        ProdutoEntity produtoEntity = produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

        List<Promocao> promocaoList = promocaoRepository.findByProdutoEntity(produtoEntity)
                .stream().map(promocaoEntity -> promocaoEntityMapper.toDomain(promocaoEntity))
                .collect(Collectors.toUnmodifiableList());
        return promocaoList;
    }

    @Override
    @Transactional // Ativar manualmente, irá desativar novamente após uma hora.
    public Boolean activePromotion(String id) {
        PromocaoEntity promocaoEntity = promocaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Promoção não encontrada."));

        promocaoEntity.active();
        promocaoEntity.getProdutoEntity().applyPromocao(promocaoEntity);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean disablePromotion(String id) {
        PromocaoEntity promocaoEntity = promocaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Promoção não encontrada."));
        ProdutoEntity produtoEntity = produtoRepository
                .findById(promocaoEntity.getProdutoEntity().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Promoção não encontrada."));

        promocaoEntity.disable();
        promocaoEntity.getProdutoEntity().applyPromocao(promocaoEntity);
        produtoEntity.setValor(produtoEntity.getValorOriginal());
        return Boolean.TRUE;
    }

    @Override
    public Promocao showPromotionById(String id) {
        PromocaoEntity promocaoEntity = promocaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Promoção não encontrada."));
        return promocaoEntityMapper.toDomain(promocaoEntity);
    }

    @Transactional
    @Scheduled(fixedRate = 3600000) // 1 hora
    public void handlePromotion() {
        System.out.println("\n\nHandling promotions.\n\n");
        List<PromocaoEntity> promocaoEntities = promocaoRepository.findAll();
        promocaoEntities.stream().forEach(promocaoEntity -> {
            ProdutoEntity produtoEntity = produtoRepository
                    .findById(promocaoEntity.getProdutoEntity().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

//            Ativando promocao que foi desativada manualmente a cada 1hora
//            if (promocaoEntity.getDataInicio().isBefore(LocalDateTime.now()) && !promocaoEntity.getAtivo()) {
//                promocaoEntity.setAtivo(Boolean.TRUE);
//                produtoEntity.applyPromocao(promocaoEntity);
//            }

            if (promocaoEntity.getDataFim().isBefore(LocalDateTime.now()) && promocaoEntity.getAtivo()) {
//                promocaoEntity.setAtivo(Boolean.FALSE);
//                produtoEntity.applyPromocao(promocaoEntity);
                produtoEntity.setValor(produtoEntity.getValorOriginal());
                promocaoRepository.delete(promocaoEntity);
            }

            produtoRepository.save(produtoEntity);
        });
    }
}
