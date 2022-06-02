package br.gov.mt.intermat.projeto03.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.exception.NegocioException;
import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import br.gov.mt.intermat.projeto03.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService{
    private CategoriaRepository categoriaRepository;
    public Categoria buscar (long categoriaId){
        Optional <Categoria> obj = categoriaRepository.findById(categoriaId);
        return obj.orElse(null);
       // return categoriaRepository.findById(categoriaId)
        //                  .orElseThrow(()-> new NegocioException("categoria não enccontrado"));
    }

    @Transactional
    public Categoria salvar(Categoria categoria){
        boolean nomeExiste = categoriaRepository
                              .findByNome(categoria.getNome())
                              .stream()
                              .anyMatch(categoriaExistente -> !categoriaExistente.equals(categoria));
        if (nomeExiste) {
            throw new NegocioException("jah existe um categoria cadastrado com este nome");
        }                 

        return categoriaRepository.save(categoria);
    }
    @Transactional
    public void excluir (Long categoriaId){
    categoriaRepository.deleteById(categoriaId);
    }
}