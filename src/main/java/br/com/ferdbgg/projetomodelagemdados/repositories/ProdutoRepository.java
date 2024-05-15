package br.com.ferdbgg.projetomodelagemdados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
