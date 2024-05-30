package br.com.ferdbgg.projetomodelagemdados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Cliente;
import br.com.ferdbgg.projetomodelagemdados.repositories.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente buscarPorId(Integer id) {
        final Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException(id, Cliente.class));
    }

    public List<Cliente> buscarTudo() {
        return this.clienteRepository.findAll();
    }

    public Page<Cliente> buscarTudoPaginado(PageRequest pageRequest) {
        return clienteRepository.findAll(pageRequest);
    }

}
