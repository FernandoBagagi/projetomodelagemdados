package br.com.ferdbgg.projetomodelagemdados.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ferdbgg.projetomodelagemdados.domain.Categoria;
import br.com.ferdbgg.projetomodelagemdados.repositories.CategoriaRepository;

public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria buscarPorId(Integer id) {
        if (id != null) {
            Optional<Categoria> categoria = this.categoriaRepository.findById(id);
            if (categoria.isPresent()) {
                return categoria.get();
            }
        }
        return null;
    }

}
