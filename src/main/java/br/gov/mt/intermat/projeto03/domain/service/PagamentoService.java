package br.gov.mt.intermat.projeto03.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.model.Pagamento;
import br.gov.mt.intermat.projeto03.domain.repository.PagamentoRepository;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PagamentoService{
    private PagamentoRepository pagamentoRepository;
    
    public Pagamento buscar (long pagamentoId){
        Optional <Pagamento> pagamento = pagamentoRepository.findById(pagamentoId);
        return pagamento.orElseThrow(() -> new ObjetcNotFoundException("Objeto não encontrado! Id: " + pagamentoId + ", Tipo: " + Pagamento.class.getName()));
    }    
    
    public List<Pagamento> listarTudo (){
        
        return  pagamentoRepository.findAll();
       // return pagamentoRepository.findByNome("maria soares");
        // return pagamentoRepository.findByNomeContaining("taques");
  }

    @Transactional
    public Pagamento salvar(Pagamento pagamento){
        // boolean nomeExiste = pagamentoRepository
        //                       .findByNome(pagamento.getNome())
        //                       .stream()
        //                       .anyMatch(pagamentoExistente -> !pagamentoExistente.equals(pagamento));
        // if (nomeExiste) {
        //     throw new ObjetcNotFoundException("jah existe um pagamento cadastrado com este nome! nome: " + pagamento.getNome() + ", Tipo: " + Pagamento.class.getName());
        // }                 

        return pagamentoRepository.save(pagamento);
    }
    @Transactional
    public void excluir (Long pagamentoId){
    pagamentoRepository.deleteById(pagamentoId);
    }
}