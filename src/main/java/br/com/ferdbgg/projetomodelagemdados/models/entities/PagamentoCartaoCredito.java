package br.com.ferdbgg.projetomodelagemdados.models.entities;

import br.com.ferdbgg.projetomodelagemdados.models.enums.StatusPagamentoEnum;
import jakarta.persistence.Entity;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pagamentoscartaocredito")
public class PagamentoCartaoCredito extends Pagamento {

    private String numeroCartaoCredito; //Criar restrição do formato xxxx xxxx xxxx xxxx
    
    public PagamentoCartaoCredito(StatusPagamentoEnum status, Pedido pedido, String numeroCartaoCredito) {
        super(status, pedido);
        this.numeroCartaoCredito = numeroCartaoCredito;
    }
    
}
