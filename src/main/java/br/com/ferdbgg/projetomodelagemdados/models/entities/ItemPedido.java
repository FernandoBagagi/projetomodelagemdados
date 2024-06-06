package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.ferdbgg.projetomodelagemdados.models.chavesprimariascompostas.IdItemPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private IdItemPedido id; //fazer o total encapsulamento desse atributo, tirar get, set, construtor

    private Integer quantidade;

    private BigDecimal valorDesconto; //TODO: colocar as restrições do banco

    private BigDecimal preco;

    public ItemPedido(Produto produto, Pedido pedido, Integer quantidade, BigDecimal valorDesconto, BigDecimal preco) {
        this.id = new IdItemPedido(produto, pedido);
        this.quantidade = quantidade;
        this.valorDesconto = valorDesconto;
        this.preco = preco;
    }

}
