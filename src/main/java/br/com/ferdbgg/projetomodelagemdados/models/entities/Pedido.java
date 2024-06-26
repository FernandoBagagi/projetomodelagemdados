package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Instant instanteCompra; //TODO: criar formatação no banco, ver se é mais interesante mudar pra date ao invés de usar o ignora?

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    @JsonManagedReference //TODO: Retirar
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    @JsonManagedReference //TODO: Retirar
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idEnderecoEntrega")
    @JsonManagedReference //TODO: Retirar
    private Endereco enderecoEntrega; //TODO: pensar em mudar pro toString()

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itensPedido = new HashSet<>(); //new TreeSet<>(); //TODO: implementar o comparable

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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public Date getInstanteCompra() {
        return Date.from(this.instanteCompra);
    }    

}
