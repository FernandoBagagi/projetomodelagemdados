package br.com.ferdbgg.projetomodelagemdados.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public String testeString() {
        return "Deu bom o teste";
    }

}
