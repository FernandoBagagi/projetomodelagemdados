package br.com.ferdbgg.projetomodelagemdados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Pedido;
import br.com.ferdbgg.projetomodelagemdados.repositories.PedidoRepository;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscarPorId(Integer id) {
        final Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjetoNaoEncontradoException(id, Pedido.class));
    }

    public List<Pedido> buscarTudo() {
        return this.pedidoRepository.findAll();
    }

    public Page<Pedido> buscarTudoPaginado(PageRequest pageRequest) {
        return pedidoRepository.findAll(pageRequest);
    }

}
