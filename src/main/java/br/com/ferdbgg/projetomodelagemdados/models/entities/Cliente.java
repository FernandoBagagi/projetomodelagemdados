package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ferdbgg.projetomodelagemdados.models.enums.TipoClienteEnum;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "clientes")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String cpfOuCnpj;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    @Column(nullable = false)
    private Integer tipoCliente;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<>();

    @Setter(value = AccessLevel.NONE)
    @ElementCollection
    @CollectionTable(name = "telefones")
    private Set<String> telefones = new HashSet<>();

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(String nome, String email, String cpfOuCnpj, TipoClienteEnum tipoCliente) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipoCliente = tipoCliente.getCodigo();
    }

    public TipoClienteEnum getTipoCliente() {
        return TipoClienteEnum.fromInteger(this.tipoCliente);
    }

    public void setTipoCliente(TipoClienteEnum tipoCliente) {
        this.tipoCliente = tipoCliente.getCodigo();
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos.clear();
        if(enderecos != null) {
            this.enderecos.addAll(enderecos);
        }
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones.clear();
        if(telefones != null) {
            this.telefones.addAll(telefones);
        }
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos.clear();
        if(pedidos != null) {
            this.pedidos.addAll(pedidos);
        }
    }

}
