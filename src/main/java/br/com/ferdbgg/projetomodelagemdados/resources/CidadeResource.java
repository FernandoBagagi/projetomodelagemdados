package br.com.ferdbgg.projetomodelagemdados.resources;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Cidade;
import br.com.ferdbgg.projetomodelagemdados.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

    private CidadeService cidadeService;

    public CidadeResource(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> buscarTudo() {
        return ResponseEntity.ok().body(this.cidadeService.buscarTudo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.cidadeService.buscarPorId(id));
    }

    // URL de teste:
    // http://localhost:8080/cidades/paginado?numeroPagina=0&resultadosPorPagina=1
    @GetMapping("/paginado")
    public ResponseEntity<List<Cidade>> buscarTudoPaginado(Integer numeroPagina, Integer resultadosPorPagina) {
        if (numeroPagina == null || numeroPagina < 0) {
            numeroPagina = 0; // Página padrão
        }
        if (resultadosPorPagina == null || resultadosPorPagina <= 0 || resultadosPorPagina > 10) {
            resultadosPorPagina = 10; // Tamanho padrão de resultados por página
        }
        PageRequest pageRequest = PageRequest.of(numeroPagina, resultadosPorPagina);
        return ResponseEntity.ok().body(cidadeService.buscarTudoPaginado(pageRequest).getContent());
    }

}
