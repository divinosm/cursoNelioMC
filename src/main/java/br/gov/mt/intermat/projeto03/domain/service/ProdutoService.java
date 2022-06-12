package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.exception.NegocioException;
import br.gov.mt.intermat.projeto03.domain.model.Produto;
import br.gov.mt.intermat.projeto03.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService{
    private ProdutoRepository produtoRepository;
    
    public Produto buscar (long produtoId){
        Optional <Produto> obj = produtoRepository.findById(produtoId);
        return obj.orElse(null);
       // return produtoRepository.findById(produtoId)
        //                  .orElseThrow(()-> new NegocioException("produto não enccontrado"));
    }    
    
    public List<Produto> listarTudo (){
        
        return  produtoRepository.findAll();
       // return produtoRepository.findByNome("maria soares");
        // return produtoRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Produto salvar(Produto produto){
        boolean nomeExiste = produtoRepository
                              .findByNome(produto.getNome())
                              .stream()
                              .anyMatch(produtoExistente -> !produtoExistente.equals(produto));
        if (nomeExiste) {
            throw new NegocioException("jah existe um produto cadastrado com este nome");
        }                 

        return produtoRepository.save(produto);
    }
    @Transactional
    public void excluir (Long produtoId){
    produtoRepository.deleteById(produtoId);
    }
}