package br.com.ferdbgg.projetomodelagemdados.configurations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Categoria;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Cidade;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Cliente;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Endereco;
import br.com.ferdbgg.projetomodelagemdados.models.entities.ItemPedido;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Pagamento;
import br.com.ferdbgg.projetomodelagemdados.models.entities.PagamentoBoleto;
import br.com.ferdbgg.projetomodelagemdados.models.entities.PagamentoCartaoCredito;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Pedido;
import br.com.ferdbgg.projetomodelagemdados.models.entities.Produto;
import br.com.ferdbgg.projetomodelagemdados.models.enums.EstadoEnum;
import br.com.ferdbgg.projetomodelagemdados.models.enums.StatusPagamentoEnum;
import br.com.ferdbgg.projetomodelagemdados.models.enums.TipoClienteEnum;
import br.com.ferdbgg.projetomodelagemdados.repositories.CategoriaRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.CidadeRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.ClienteRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.EnderecoRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.ItemPedidoRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.PagamentoRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.PedidoRepository;
import br.com.ferdbgg.projetomodelagemdados.repositories.ProdutoRepository;
import jakarta.validation.ConstraintViolationException;

@Configuration
public class Instanciacao implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CidadeRepository cidadeRepository;
    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public Instanciacao(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CidadeRepository cidadeRepository, EnderecoRepository enderecoRepository, ClienteRepository clienteRepository, PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
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
        produtosDumb.add(new Produto("Impressora", BigDecimal.valueOf(879.13)));
        produtosDumb.get(1).getCategorias().add(categoriaDumb.get(0));
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
        this.clienteRepository.save(cliente1);

        List<Endereco> enderecosDumb = new ArrayList<>();
        enderecosDumb.add(new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38220834", cidadesDumb.get(0), cliente1));
        enderecosDumb.add(new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38220834", cidadesDumb.get(0), cliente1));
        this.enderecoRepository.saveAll(enderecosDumb);

        cliente1.getEnderecos().addAll(Arrays.asList(enderecosDumb.get(0), enderecosDumb.get(1)));
        this.clienteRepository.save(cliente1);

        SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        List<Pedido> pedidosDumb = new ArrayList<>();
        pedidosDumb.add(new Pedido(formatadorData.parse("30/09/2017 10:32").toInstant(), null, cliente1, enderecosDumb.get(0)));
        pedidosDumb.add(new Pedido(formatadorData.parse("10/10/2017 09:14").toInstant(), null, cliente1, enderecosDumb.get(1)));

        Pagamento pagamento1 = new PagamentoCartaoCredito(StatusPagamentoEnum.PENDENTE, pedidosDumb.get(0), "1234 1234 1234 1234");
        pedidosDumb.get(0).setPagamento(pagamento1);
        Pagamento pagamento2 = new PagamentoBoleto(StatusPagamentoEnum.CANCELADO, pedidosDumb.get(1), formatadorData.parse("10/10/2017 09:14"), formatadorData.parse("20/10/2017 09:14"));
        pedidosDumb.get(1).setPagamento(pagamento2);

        this.pedidoRepository.saveAll(pedidosDumb);

        this.pagamentoRepository.save(pagamento1);
        this.pagamentoRepository.save(pagamento2);

        ItemPedido ip1 = new ItemPedido(produtosDumb.get(0), pedidosDumb.get(0), 2, null, new BigDecimal("2000"));
        ItemPedido ip2 = new ItemPedido(produtosDumb.get(0), pedidosDumb.get(1), 2, new BigDecimal("200"), new BigDecimal("2000"));
        ItemPedido ip3 = new ItemPedido(produtosDumb.get(1), pedidosDumb.get(1), 2, new BigDecimal("200"), new BigDecimal("500"));

        pedidosDumb.get(0).getItensPedido().add(ip1);
        pedidosDumb.get(1).getItensPedido().addAll(Arrays.asList(ip2, ip3));

        produtosDumb.get(0).getItensPedido().addAll(Arrays.asList(ip1, ip2));
        produtosDumb.get(1).getItensPedido().add(ip3);

        this.itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }

}
