package br.com.lgmanagement.lgManagement.infra.controller.produto.request;

import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.util.List;

public record UpdateProdutoRequest(
        String nome,

        @Pattern(regexp = "^\\d+$")
        String codigo,
        BigDecimal valor,
        List<Categoria> categorias,
        BigDecimal estoque,
        BigDecimal valorCompra
) {
}
