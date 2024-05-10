package br.com.ferdbgg.projetomodelagemdados.configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ferdbgg.projetomodelagemdados.models.Categoria;
import br.com.ferdbgg.projetomodelagemdados.repositories.CategoriaRepository;

@Configuration
public class Instanciacao implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    
    public Instanciacao(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //this.categoriaRepository.deleteAll();

        List<Categoria> categoriaDumb = new ArrayList<>();
        categoriaDumb.add(new Categoria(null, "Informática"));
        categoriaDumb.add(new Categoria(null, "Escritório"));
        this.categoriaRepository.saveAll(categoriaDumb);

    }

}
