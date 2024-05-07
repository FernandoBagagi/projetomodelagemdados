package br.com.ferdbgg.projetomodelagemdados.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferdbgg.projetomodelagemdados.domain.Categoria;
import br.com.ferdbgg.projetomodelagemdados.services.CategoriaService;



@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
    
    private CategoriaService categoriaService;

    @Autowired
    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> testeString() {

        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(0, "Teste 1"));
        categorias.add(new Categoria(0, "Teste 2"));
        categorias.add(new Categoria(1, "Teste 3"));

        Optional<String> hashes = categorias.stream().map(Categoria::hashCode).map(String::valueOf).reduce((s1, s2) -> s1 + ", " + s2);
        hashes.ifPresent(System.out::println);

        System.out.println(categorias.get(0).equals(categorias.get(1)));
        System.out.println(categorias.get(0).equals(categorias.get(2)));
        System.out.println(categorias.get(1).equals(categorias.get(2)));
        
        return categorias;

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.categoriaService.buscarPorId(id));
    }

}
