package br.com.lgmanagement.lgManagement.infra.gateways;

import br.com.lgmanagement.lgManagement.application.gateways.produto.ProdutoGateway;
import br.com.lgmanagement.lgManagement.domain.entities.produto.Produto;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoRepositoryGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    public ProdutoRepositoryGateway(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    @Transactional
    public Produto createProduto(Produto produto) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository
                .findByCodigo(produto.getCodigo());

        if (produtoEntity.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Código: " + produto.getCodigo() + " já cadastrado no sistema.");
        }

        ProdutoEntity entity = produtoEntityMapper.toEntity(produto);
        produtoRepository.save(entity);
        return produtoEntityMapper.toDomain(entity);
    }

    @Override
    public List<Produto> showAllProdutos() {
        return produtoRepository.findAll().stream()
                .map(produtoEntity -> produtoEntityMapper.toDomain(produtoEntity))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public Produto updateProduto(String id, Produto produto) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));

        produtoEntity.updateProduto(
                produto.getNome(),
                produto.getCodigo(),
                produto.getValor(),
                produto.getCategorias(),
                produto.getEstoque()
        );
        return produtoEntityMapper.toDomain(produtoRepository.save(produtoEntity));
    }

    @Override
    @Transactional
    public void deleteProduto(String id) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));
        produtoEntity.delete();
    }


    @Override
    public Produto showProduto(String id) {
        return produtoEntityMapper.toDomain(
                produtoRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado.")));
    }

    @Override
    @Transactional
    public void activeProduto(String id) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado."));
        produtoEntity.restore();
    }

    @Override
    public List<Produto> showProdutosWithLowStock() {
        List<Produto> produtos = produtoRepository.findByLowStock().stream()
                .map(produtoEntity -> produtoEntityMapper.toDomain(produtoEntity))
                .collect(Collectors.toUnmodifiableList());
        return produtos;
    }

    @Override
    public List<Produto> showAllProdutosAtivos() {
        List<Produto> produtosAtivos = produtoRepository.findAllByAtivoTrue().stream()
                .map(produtoEntity -> produtoEntityMapper.toDomain(produtoEntity))
                .collect(Collectors.toUnmodifiableList());
        return produtosAtivos;
    }
}
