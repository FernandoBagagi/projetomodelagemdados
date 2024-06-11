package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "pagamentosboleto")
public class PagamentoBoleto extends Pagamento {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date vencimento; //Criar as restrições do banco

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date pagamento; //Criar as restrições do banco

    public PagamentoBoleto(StatusPagamentoEnum status, Pedido pedido, Date vencimento, Date pagamento) {
        super(status, pedido);
        this.vencimento = vencimento;
        this.pagamento = pagamento;
    }
    
}
