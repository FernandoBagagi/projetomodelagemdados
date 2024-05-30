package br.com.ferdbgg.projetomodelagemdados.resources;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Cliente;
import br.com.ferdbgg.projetomodelagemdados.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    private ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTudo() {
        return ResponseEntity.ok().body(this.clienteService.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.clienteService.buscarPorId(id));
    }

    // URL de teste:
    // http://localhost:8080/clientes/paginado?numeroPagina=0&resultadosPorPagina=1
    @GetMapping("/paginado")
    public ResponseEntity<List<Cliente>> buscarTudoPaginado(Integer numeroPagina, Integer resultadosPorPagina) {
        if (numeroPagina == null || numeroPagina < 0) {
            numeroPagina = 0; // Página padrão
        }
        if (resultadosPorPagina == null || resultadosPorPagina <= 0 || resultadosPorPagina > 10) {
            resultadosPorPagina = 10; // Tamanho padrão de resultados por página
        }
        PageRequest pageRequest = PageRequest.of(numeroPagina, resultadosPorPagina);
        return ResponseEntity.ok().body(clienteService.buscarTudoPaginado(pageRequest).getContent());
    }

}
