package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.exception.NegocioException;
import br.gov.mt.intermat.projeto03.domain.model.Cidade;
import br.gov.mt.intermat.projeto03.domain.repository.CidadeRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CidadeService{
    private CidadeRepository cidadeRepository;
    
    public Cidade buscar (long cidadeId){
        Optional <Cidade> cidade = cidadeRepository.findById(cidadeId);
        return cidade.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + cidadeId + ", Tipo: " + Cidade.class.getName()));
    }    
    
    public List<Cidade> listarTudo (){
        
        return  cidadeRepository.findAll();
       // return cidadeRepository.findByNome("maria soares");
        // return cidadeRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Cidade salvar(Cidade cidade){
        boolean nomeExiste = cidadeRepository
                              .findByNome(cidade.getNome())
                              .stream()
                              .anyMatch(cidadeExistente -> !cidadeExistente.equals(cidade));
        if (nomeExiste) {
            throw new NegocioException("jah existe um cidade cadastrado com este nome");
        }                 

        return cidadeRepository.save(cidade);
    }
    @Transactional
    public void excluir (Long cidadeId){
    cidadeRepository.deleteById(cidadeId);
    }
}
