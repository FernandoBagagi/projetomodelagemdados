package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ferdbgg.projetomodelagemdados.models.enums.StatusPagamentoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "pagamentos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    protected static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    protected Integer id;

    @Enumerated(EnumType.STRING)
    protected StatusPagamentoEnum status;

    @OneToOne
    @JoinColumn(name = "idPedido")
    @MapsId
    @JsonBackReference//TODO: Trocar por Jsonignore, precisa mesmo dessa referência pra pedido?
    protected Pedido pedido;

    protected Pagamento(StatusPagamentoEnum status, Pedido pedido) {
        this.status = status;
        this.pedido = pedido;
    }
    
}
