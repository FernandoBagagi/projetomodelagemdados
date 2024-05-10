package br.com.ferdbgg.projetomodelagemdados.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ferdbgg.projetomodelagemdados.models.Entities.Categoria;
import br.com.ferdbgg.projetomodelagemdados.repositories.CategoriaRepository;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

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
