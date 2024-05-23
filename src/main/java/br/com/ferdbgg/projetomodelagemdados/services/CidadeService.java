package br.com.ferdbgg.projetomodelagemdados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Cidade;
import br.com.ferdbgg.projetomodelagemdados.repositories.CidadeRepository;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Cidade buscarPorId(Integer id) {
        final Optional<Cidade> cidade = this.cidadeRepository.findById(id);
        return cidade.orElseThrow(() -> new ObjetoNaoEncontradoException(id, Cidade.class));
    }

    public List<Cidade> buscarTudo() {
        return this.cidadeRepository.findAll();
    }

    public Page<Cidade> buscarTudoPaginado(PageRequest pageRequest) {
        return cidadeRepository.findAll(pageRequest);
    }

}
