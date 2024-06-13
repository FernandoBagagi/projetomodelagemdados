package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false, columnDefinition = "DECIMAL(15, 2) DEFAULT '0.00'")
    @DecimalMin(value = "0.00", message = "Não é permitido salvar um produto com preço negativo")
    private BigDecimal preco;

    @Setter(value = AccessLevel.NONE)
    @JsonManagedReference //TODO: retirar
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "produtos_categorias",
        joinColumns = @JoinColumn(name = "idProduto"),
        inverseJoinColumns = @JoinColumn(name = "idCategoria")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "id.produto")
    @JsonIgnore
    private Set<ItemPedido> itensPedido = new HashSet<>();

    public Produto(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public void setCategorias(List<Categoria> categorias) { //TODO: mudar pra collection
        this.categorias.clear();
        if(categorias != null) {
            this.categorias.addAll(categorias);
        }
    }

    //TODO: tentar ver se é melhor mudar tudo pra Collection
    public void setItensPedido(Collection<ItemPedido> itensPedido) {
        this.itensPedido.clear();
        if(itensPedido != null) {
            this.itensPedido.addAll(itensPedido);
        }
    }

    @JsonIgnore
    public List<Pedido> getPedidos() {
        return this.getItensPedido().stream().map(i -> i.getId().getPedido()).toList(); //TODO: mudat para Item::getPedido
    }

}
