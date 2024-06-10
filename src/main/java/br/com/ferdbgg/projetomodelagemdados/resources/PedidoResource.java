package br.com.ferdbgg.projetomodelagemdados.resources;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Pedido;
import br.com.ferdbgg.projetomodelagemdados.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    private PedidoService pedidoService;

    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTudo() {
        return ResponseEntity.ok().body(this.pedidoService.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.pedidoService.buscarPorId(id));
    }

    // URL de teste:
    // http://localhost:8080/pedidos/paginado?numeroPagina=0&resultadosPorPagina=1
    @GetMapping("/paginado")
    public ResponseEntity<List<Pedido>> buscarTudoPaginado(Integer numeroPagina, Integer resultadosPorPagina) {
        if (numeroPagina == null || numeroPagina < 0) {
            numeroPagina = 0; // Página padrão
        }
        if (resultadosPorPagina == null || resultadosPorPagina <= 0 || resultadosPorPagina > 10) {
            resultadosPorPagina = 10; // Tamanho padrão de resultados por página
        }
        PageRequest pageRequest = PageRequest.of(numeroPagina, resultadosPorPagina);
        return ResponseEntity.ok().body(pedidoService.buscarTudoPaginado(pageRequest).getContent());
    }

}
