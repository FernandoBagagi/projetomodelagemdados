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
import jakarta.validation.ConstraintViolationException;

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

        this.categoriaRepository.deleteAll();
        this.produtoRepository.deleteAll();

        List<Categoria> categoriaDumb = new ArrayList<>();
        categoriaDumb.add(new Categoria(null, "Informática"));
        categoriaDumb.add(new Categoria(null, "Escritório"));
        this.categoriaRepository.saveAll(categoriaDumb);

        try {
            this.produtoRepository.save(new Produto("Preço negativo", new BigDecimal(-1)));
        } catch (ConstraintViolationException e) {
            //System.err.println(e.getMessage());
            System.err.println(e.getLocalizedMessage());
            e.getConstraintViolations().forEach(constraint -> System.err.println(constraint.getMessageTemplate()));
            //System.err.println(e.getConstraintViolations());
        }


        List<Produto> produtosDumb = new ArrayList<>();
        produtosDumb.add(new Produto("Monitor", BigDecimal.valueOf(0.1)));
        produtosDumb.get(0).getCategorias().add(categoriaDumb.get(0));
        this.produtoRepository.saveAll(produtosDumb);

    }

}
