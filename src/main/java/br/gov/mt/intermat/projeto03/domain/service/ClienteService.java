package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.mt.intermat.projeto03.domain.model.Cliente;
import br.gov.mt.intermat.projeto03.domain.repository.ClienteRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService{
    private ClienteRepository clienteRepository;
    
    public Cliente buscar (long clienteId){
        Optional <Cliente> cliente = clienteRepository.findById(clienteId);
        return cliente.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + clienteId + ", Tipo: " + Cliente.class.getName()));
    }    
    
    public List<Cliente> listarTudo (){
        
        return  clienteRepository.findAll();
       // return clienteRepository.findByNome("maria soares");
        // return clienteRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean nomeExiste = clienteRepository
                              .findByNome(cliente.getNome())
                              .stream()
                              .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (nomeExiste) {
            throw new ObjetcNotFoundException("jah existe um cliente cadastrado com este nome! Nome: " + cliente.getNome() + ", Tipo: " + Cliente.class.getName());
        }                 

        return clienteRepository.save(cliente);
    }
    @Transactional
    public void excluir (Long clienteId){
    clienteRepository.deleteById(clienteId);
    }
}
