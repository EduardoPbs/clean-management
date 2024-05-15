package br.com.lgmanagement.lgManagement.config;

import br.com.lgmanagement.lgManagement.infra.persistence.item.ItemEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.produto.ProdutoRepository;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoEntityMapper;
import br.com.lgmanagement.lgManagement.infra.persistence.transacao.TransacaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfig {

    @Bean
    ItemEntityMapper itemEntityMapper(
            ProdutoRepository produtoRepository,
            ProdutoEntityMapper produtoEntityMapper,
            TransacaoRepository transacaoRepository,
            TransacaoEntityMapper transacaoEntityMapper
    ) {
        return new ItemEntityMapper(
                produtoRepository,
                produtoEntityMapper,
                transacaoRepository,
                transacaoEntityMapper
        );
    }
}
