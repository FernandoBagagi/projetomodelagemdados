package br.com.ferdbgg.projetomodelagemdados.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferdbgg.projetomodelagemdados.models.entities.Produto;
import br.com.ferdbgg.projetomodelagemdados.services.ProdutoService;



@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
    
    private ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.produtoService.buscarPorId(id));
    }

}
