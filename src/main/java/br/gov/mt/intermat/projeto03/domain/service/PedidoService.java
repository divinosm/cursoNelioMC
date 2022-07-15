package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.model.Pedido;
import br.gov.mt.intermat.projeto03.domain.repository.PedidoRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService{
    private PedidoRepository pedidoRepository;
    
    public Pedido buscar (long pedidoId){
        Optional <Pedido> pedido = pedidoRepository.findById(pedidoId);
        return pedido.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + pedidoId + ", Tipo: " + Pedido.class.getName()));
    }    
    
    public List<Pedido> listarTudo (){
        
        return  pedidoRepository.findAll();
       // return pedidoRepository.findByNome("maria soares");
        // return pedidoRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Pedido salvar(Pedido pedido){
        // boolean nomeExiste = pedidoRepository
        //                       .findByNome(pedido.getNome())
        //                       .stream()
        //                       .anyMatch(pedidoExistente -> !pedidoExistente.equals(pedido));
        // if (nomeExiste) {
        //     throw new ObjetcNotFoundException("jah existe um pedido cadastrado com este nome! nome: " + pedido.getNome() + ", Tipo: " + Pedido.class.getName());
        // }                 

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizar(Pedido obj){
        buscar(obj.getId());
        return pedidoRepository.save(obj);
    }

    @Transactional
    public void excluir (Long pedidoId){
    pedidoRepository.deleteById(pedidoId);
    }
}