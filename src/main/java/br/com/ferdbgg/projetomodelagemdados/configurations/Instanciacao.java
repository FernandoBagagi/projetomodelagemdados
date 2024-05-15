package br.com.ferdbgg.projetomodelagemdados.configurations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Categoria;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Produto;
import br.com.ferdbgg.projetomodelagemdados.repositories.CategoriaRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.ProdutoRepository;

@Configuration
public class Instanciacao implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    
    public Instanciacao(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //this.categoriaRepository.deleteAll();

        List<Categoria> categoriaDumb = new ArrayList<>();
        categoriaDumb.add(new Categoria(null, "Informática"));
        categoriaDumb.add(new Categoria(null, "Escritório"));
        this.categoriaRepository.saveAll(categoriaDumb);


        List<Produto> produtosDumb = new ArrayList<>();
        produtosDumb.add(new Produto(null, "Preço negativo", new BigDecimal(-1), categoriaDumb));
        this.produtoRepository.saveAll(produtosDumb);

    }

}
