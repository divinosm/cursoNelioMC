package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.model.Endereco;
import br.gov.mt.intermat.projeto03.domain.repository.EnderecoRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnderecoService{
    private EnderecoRepository enderecoRepository;
    
    public Endereco buscar (long enderecoId){
        Optional <Endereco> endereco = enderecoRepository.findById(enderecoId);
        return endereco.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + enderecoId + ", Tipo: " + Endereco.class.getName()));
    }    
    
    public List<Endereco> listarTudo (){
        
        return  enderecoRepository.findAll();
       // return enderecoRepository.findByNome("maria soares");
        // return enderecoRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Endereco salvar(Endereco endereco){
        // boolean nomeExiste = enderecoRepository
        //                       .findByNome(endereco.getNome())
        //                       .stream()
        //                       .anyMatch(enderecoExistente -> !enderecoExistente.equals(endereco));
        // if (nomeExiste) {
        //     throw new NegocioException("jah existe um endereco cadastrado com este nome");
        // }                 

        return enderecoRepository.save(endereco);
    }
    @Transactional
    public void excluir (Long enderecoId){
    enderecoRepository.deleteById(enderecoId);
    }
}
