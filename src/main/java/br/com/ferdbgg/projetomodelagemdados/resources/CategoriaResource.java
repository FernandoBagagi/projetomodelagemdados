package br.com.ferdbgg.projetomodelagemdados.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ferdbgg.projetomodelagemdados.domain.Categoria;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> testeString() {
        
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria(0, "Teste 1"));
        categorias.add(new Categoria(0, "Teste 2"));
        categorias.add(new Categoria(1, "Teste 3"));
        
        return categorias;

    }

}
