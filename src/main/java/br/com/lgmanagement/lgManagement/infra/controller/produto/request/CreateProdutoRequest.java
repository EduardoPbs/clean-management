package br.com.lgmanagement.lgManagement.infra.controller.produto.request;

import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record CreateProdutoRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @NotBlank
        @Pattern(regexp = "^\\d+$", message = "Código deve conter apenas números.")
        String codigo,

        @NotNull(message = "Valor é obrigatório.")
        BigDecimal valor,

        @NotNull
        @Size(min = 1, message = "Deve conter pelo menos uma categoria.")
        List<Categoria> categorias,

        @NotNull(message = "Estoque é obrigatório.")
        BigDecimal estoque,

        @NotNull(message = "Ativo é obrigatório.")
        Boolean ativo
) {
}
