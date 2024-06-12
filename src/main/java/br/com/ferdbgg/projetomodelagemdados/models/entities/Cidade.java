package br.com.ferdbgg.projetomodelagemdados.models.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.ferdbgg.projetomodelagemdados.models.enums.EstadoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cidades")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "uf")
    @JsonProperty("uf")
    private EstadoEnum estado;

    public Cidade(String nome, EstadoEnum estado) {
        this.nome = nome;
        this.estado = estado;
    }

}
