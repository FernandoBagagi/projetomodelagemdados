package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "produtos_categorias",
        joinColumns = @JoinColumn(name = "idProduto"),
        inverseJoinColumns = @JoinColumn(name = "idCategoria")
    )
    private List<Categoria> categorias = new ArrayList<>();

    public void setCategorias(List<Categoria> categorias) {
        this.categorias.clear();
        if(categorias != null) {
            this.categorias.addAll(categorias);
        }
    }

}
