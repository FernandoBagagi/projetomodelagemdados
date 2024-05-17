package br.com.ferdbgg.projetomodelagemdados.models;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErroPadrao implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;

    private String mensagem;

    private Instant instante;
    
}
