package br.com.ferdbgg.projetomodelagemdados.services;

import java.util.Optional;
import org.springframework.stereotype.Service;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Produto;
import br.com.ferdbgg.projetomodelagemdados.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto buscarPorId(Integer id) {
        final Optional<Produto> produto = this.produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjetoNaoEncontradoException(id, Produto.class));
    }

}
