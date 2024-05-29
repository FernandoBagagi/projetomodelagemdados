package br.com.ferdbgg.projetomodelagemdados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Endereco;
import br.com.ferdbgg.projetomodelagemdados.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco buscarPorId(Integer id) {
        final Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new ObjetoNaoEncontradoException(id, Endereco.class));
    }

    public List<Endereco> buscarTudo() {
        return this.enderecoRepository.findAll();
    }

    public Page<Endereco> buscarTudoPaginado(PageRequest pageRequest) {
        return enderecoRepository.findAll(pageRequest);
    }

}
