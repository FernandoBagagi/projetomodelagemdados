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
public abstract class PagamentoCartaoCredito extends Pagamento {

    private String numeroCartaoCredito;
    
    protected PagamentoCartaoCredito(StatusPagamentoEnum status, String numeroCartaoCredito) {
        super(status);
        this.numeroCartaoCredito = numeroCartaoCredito;
    }
    
}
