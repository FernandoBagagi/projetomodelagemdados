package br.com.ferdbgg.projetomodelagemdados.models.chavesprimariascompostas;

import java.io.Serializable;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Pedido;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Produto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class IdItemPedido implements Serializable  {
    
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

}
