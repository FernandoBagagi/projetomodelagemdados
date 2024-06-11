package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ferdbgg.projetomodelagemdados.models.chavesprimariascompostas.IdItemPedido;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@AllArgsConstructor TODO: retirar
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "itenspedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @EmbeddedId
    @JsonIgnore //TODO ignorar ou retornar só os ids?
    private IdItemPedido id; //fazer o total encapsulamento desse atributo, tirar get, set, construtor

    private Integer quantidade; //TODO criar restrições do banco

    private BigDecimal valorDesconto; //TODO: colocar as restrições do banco

    private BigDecimal preco; //TODO: colocar as restrições do banco

    public ItemPedido(Produto produto, Pedido pedido, Integer quantidade, BigDecimal valorDesconto, BigDecimal preco) {
        this.id = new IdItemPedido(produto, pedido);
        this.quantidade = quantidade;
        this.valorDesconto = valorDesconto;
        this.preco = preco;
    }

    //TODO: fazer getPedido e get Produto, coloca @JsonIgnore somente no getPedido

}
