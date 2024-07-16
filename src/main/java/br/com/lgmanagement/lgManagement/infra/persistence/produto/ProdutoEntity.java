package br.com.lgmanagement.lgManagement.infra.persistence.produto;

import br.com.lgmanagement.lgManagement.domain.entities.Categoria;
import br.com.lgmanagement.lgManagement.infra.persistence.interfaces.iproduto.IProdutoEntity;
import br.com.lgmanagement.lgManagement.infra.persistence.promocao.PromocaoEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "produtos")
@Entity
@EqualsAndHashCode(of = "id")
public class ProdutoEntity implements IProdutoEntity {

    @Id
    private String id;

    private String nome;
    private BigDecimal valor;
    private BigDecimal valorOriginal;
    private Boolean ativo;

    @Column(unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "produtoEntity", cascade = CascadeType.ALL)
    private List<PromocaoEntity> promocaoEntity;

    @Column(precision = 38, scale = 3)
    private BigDecimal estoque;

    public ProdutoEntity() {
    }

    public ProdutoEntity(
            String nome,
            String codigo,
            BigDecimal valor,
            List<Categoria> categorias,
            BigDecimal estoque,
            Boolean ativo
    ) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.valor = valor;
        this.ativo = ativo;
        this.codigo = codigo;
        this.categorias = categorias;
        this.estoque = estoque;
        this.valorOriginal = valor;
    }

    public ProdutoEntity(
            String id,
            String nome,
            String codigo,
            BigDecimal valor,
            List<Categoria> categorias,
            BigDecimal estoque,
            Boolean ativo,
            BigDecimal valorOriginal
    ) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.ativo = ativo;
        this.codigo = codigo;
        this.categorias = categorias;
        this.estoque = estoque;
        this.valorOriginal = valorOriginal;
    }

    public Boolean applyPromocao(PromocaoEntity promocaoEntity) {
        try {
            if (promocaoEntity.getAtivo()) {
                BigDecimal valorDesconto = this.getValorOriginal()
                        .multiply(promocaoEntity.getPorcentagemDesconto())
                        .divide(new BigDecimal("100"));
                this.setValor(this.getValorOriginal().subtract(valorDesconto));
            } else {
                this.setValor(this.valorOriginal);
            }

            return Boolean.TRUE;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falha ao aplicar promoção." + e.getMessage());
        }
    }

    public PromocaoEntity getPromocaoAtiva() {
        for (PromocaoEntity promocao : this.getPromocaoEntity()) {
            if (promocao.getDataInicio().isBefore(LocalDateTime.now()) &&
                    promocao.getDataFim().isAfter(LocalDateTime.now())) {
                return promocao;
            }
        }
        return null;
    }

    public Boolean hasPromocaoAtiva() {
        for (PromocaoEntity promocao : this.getPromocaoEntity()) {
            if (promocao.getDataInicio().isBefore(LocalDateTime.now()) &&
                    promocao.getDataFim().isAfter(LocalDateTime.now())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public void updateProduto(
            String nome,
            String codigo,
            BigDecimal valor,
            List<Categoria> categorias,
            BigDecimal estoque
    ) {
        if (nome != null) {
            this.setNome(nome);
        }

        if (codigo != null) {
            this.codigo = codigo;
        }

        if (valor != null) {
            this.setValor(valor);
            this.setValorOriginal(valor);
        }

        if (categorias != null) {
            this.categorias = categorias;
        }

        if (estoque != null) {
            this.estoque = estoque;
        }
    }

    @Override
    public void delete() {
        this.ativo = false;
    }

    @Override
    public void restore() {
        this.ativo = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorOriginal() {
        return this.valorOriginal;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

    public List<PromocaoEntity> getPromocaoEntity() {
        return promocaoEntity;
    }

    public void setPromocaoEntity(List<PromocaoEntity> promocaoEntity) {
        this.promocaoEntity = promocaoEntity;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "ProdutoEntity{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", ativo=" + ativo +
                ", codigo='" + codigo + '\'' +
                ", categorias=" + categorias +
                ", estoque=" + estoque +
                '}';
    }
}
