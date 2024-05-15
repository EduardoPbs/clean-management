package br.com.lgmanagement.lgManagement.domain.entities.produto;

import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import br.com.lgmanagement.lgManagement.domain.entities.interfaces.iproduto.IProduto;
import br.com.lgmanagement.lgManagement.domain.entities.promocao.Promocao;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

public class Produto implements IProduto {

    private String nome;
    private BigDecimal valor;
    private BigDecimal valorOriginal;
    private Boolean ativo;
    private String codigo;
    private List<Categoria> categorias;
    private Promocao promocao;
    private BigDecimal estoque;

    public Produto() {
    }

    public Produto(
            String nome,
            String codigo,
            BigDecimal valor,
            List<Categoria> categorias,
            BigDecimal estoque,
            Boolean ativo
    ) {

        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor não pode ser menor ou igual a 0.00.");
        }

        if (estoque.compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque não pode ser menor ou igual a 0.00.");
        }

        this.nome = nome;
        this.valor = valor;
        this.valorOriginal = valor;
        this.ativo = ativo;
        this.codigo = codigo;
        this.categorias = categorias;
        this.estoque = estoque;
    }

    @Override
    public void delete() {
        this.ativo = false;
    }

    @Override
    public void restore() {
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", ativo=" + ativo +
                ", codigo='" + codigo + '\'' +
                ", categorias=" + categorias +
                ", estoque=" + estoque +
                "} " + super.toString();
    }
}
