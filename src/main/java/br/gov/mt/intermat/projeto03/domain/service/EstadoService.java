package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.model.Estado;
import br.gov.mt.intermat.projeto03.domain.repository.EstadoRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstadoService{
    private EstadoRepository estadoRepository;
    
    public Estado buscar (long estadoId){
        Optional <Estado> estado = estadoRepository.findById(estadoId);
        return estado.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + estadoId + ", Tipo: " + Estado.class.getName()));
    }    
    
    public List<Estado> listarTudo (){
        
        return  estadoRepository.findAll();
       // return estadoRepository.findByNome("maria soares");
        // return estadoRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Estado salvar(Estado estado){
        boolean nomeExiste = estadoRepository
                              .findByNome(estado.getNome())
                              .stream()
                              .anyMatch(estadoExistente -> !estadoExistente.equals(estado));
        if (nomeExiste) {
            throw new ObjetcNotFoundException("jah existe um estado cadastrado com este nome! Nome: " + estado.getNome() + ", Tipo: " + Estado.class.getName());
            
        }                 

        return estadoRepository.save(estado);
    }
    @Transactional
    public void excluir (Long estadoId){
    estadoRepository.deleteById(estadoId);
    }
}