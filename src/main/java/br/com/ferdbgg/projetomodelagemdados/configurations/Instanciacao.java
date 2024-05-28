package br.com.ferdbgg.projetomodelagemdados.configurations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Categoria;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Cidade;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Cliente;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Endereco;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Produto;
import br.com.ferdbgg.projetomodelagemdados.models.enums.EstadoEnum;
import br.com.ferdbgg.projetomodelagemdados.models.enums.TipoClienteEnum;
import br.com.ferdbgg.projetomodelagemdados.repositories.CategoriaRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.CidadeRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.ProdutoRepository;
import jakarta.validation.ConstraintViolationException;

@Configuration
public class Instanciacao implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    
    public Instanciacao(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CidadeRepository cidadeRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
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

        List<Cidade> cidadesDumb = new ArrayList<>();
        cidadesDumb.add(new Cidade("São Paulo", EstadoEnum.SP));
        cidadesDumb.add(new Cidade("Campinas", EstadoEnum.SP));
        cidadesDumb.add(new Cidade("Uberlândia", EstadoEnum.MG));
        cidadesDumb.add(new Cidade("Manaus", EstadoEnum.AM));
        this.cidadeRepository.saveAll(cidadesDumb);

        Cliente cliente1 = new Cliente("Maria", "maria@gmail.com", "111.222.333-44", TipoClienteEnum.PESSOAFISICA);
        cliente1.getTelefones().add("+55 (11) 12345-6789");
        cliente1.getTelefones().add("+55 (22) 54321-9876");

        Endereco endereco1 = new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38220834", cidadesDumb.get(0), cliente1);
        Endereco endereco2 = new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38220834", cidadesDumb.get(0), cliente1);

        cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
        

    }

}
