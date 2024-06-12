package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String nome;

    @Setter(value = AccessLevel.NONE)
    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void setProdutos(Collection<Produto> produtos) {
        this.produtos.clear();
        if (produtos != null) {
            this.produtos.addAll(produtos);
        }
    }

    // Essa classe implementa o equals e o hashCode dessa mandeira para caso o id
    // seja o mesmo o equals verificar se todos os campos são iguais mesmo, nesse
    // caso o hash pode ser igual mas o equals pode ser diferente. Por conceito isso
    // pode acontecer mas na prática o ideal é que o hashCode seja único e coincida
    // com o equals. Portanto, como isso é um código de estudo vai ficar essa
    // anotação dessa possibilidade mas na prática vai ser usado o equals e hashCode
    // do lombok usando somente o id

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categoria other = (Categoria) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (produtos == null) {
            if (other.produtos != null)
                return false;
        } else if (!produtos.equals(other.produtos))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}
