package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import br.gov.mt.intermat.projeto03.domain.repository.CategoriaRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService{
    private CategoriaRepository categoriaRepository;
    
    public Categoria buscar (long categoriaId){
        Optional <Categoria> categoria = categoriaRepository.findById(categoriaId);
        return categoria.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + categoriaId + ", Tipo: " + Categoria.class.getName()));
    }    
    
    public List<Categoria> listarTudo (){
        
        return  categoriaRepository.findAll();
       // return categoriaRepository.findByNome("maria soares");
        // return categoriaRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Categoria salvar(Categoria categoria){
        boolean nomeExiste = categoriaRepository
                              .findByNome(categoria.getNome())
                              .stream()
                              .anyMatch(categoriaExistente -> !categoriaExistente.equals(categoria));
        if (nomeExiste) {
            throw new ObjetcNotFoundException("jah existe um categoria cadastrado com este nome! nome: " + categoria.getNome() + ", Tipo: " + Categoria.class.getName());
        }                 

        return categoriaRepository.save(categoria);
    }
    @Transactional
    public void excluir (Long categoriaId){
    categoriaRepository.deleteById(categoriaId);
    }
}