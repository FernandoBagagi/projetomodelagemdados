package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private Instant instanteCompra;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    @JsonManagedReference
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonManagedReference
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idEnderecoEntrega")
    @JsonManagedReference
    private Endereco enderecoEntrega;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itensPedido = new HashSet<>(); //new TreeSet<>();

    public Pedido(Instant instanteCompra, Pagamento pagamento, Cliente cliente, Endereco enderecoEntrega) {
        this.instanteCompra = instanteCompra;
        this.pagamento = pagamento;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
    }

    public void setItensPedido(Collection<ItemPedido> itensPedido) {
        this.itensPedido.clear();
        if(itensPedido != null) {
            this.itensPedido.addAll(itensPedido);
        }
    }

}
