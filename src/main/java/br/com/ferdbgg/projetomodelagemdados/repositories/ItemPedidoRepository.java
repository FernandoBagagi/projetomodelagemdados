package br.com.ferdbgg.projetomodelagemdados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ferdbgg.projetomodelagemdados.models.chavesprimariascompostas.IdItemPedido;
import br.com.ferdbgg.projetomodelagemdados.models.entities.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, IdItemPedido> {
    
}
