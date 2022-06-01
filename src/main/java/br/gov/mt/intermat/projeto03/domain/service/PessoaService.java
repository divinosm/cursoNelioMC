package br.gov.mt.intermat.projeto03.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.exception.NegocioException;
import br.gov.mt.intermat.projeto03.domain.model.Pessoa;
import br.gov.mt.intermat.projeto03.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService{
    PessoaRepository pessoaRepository;
    public Pessoa buscar (long pessoaId){
        return pessoaRepository.findById(pessoaId)
                          .orElseThrow(()-> new NegocioException("pessoa não enccontrado"));
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa){
        boolean emailExiste = pessoaRepository
                              .findByEmail(pessoa.getEmail())
                              .stream()
                              .anyMatch(pessoaExistente -> !pessoaExistente.equals(pessoa));
        if (emailExiste) {
            throw new NegocioException("jah existe um pessoa cadastrado com este email");
        }                 

        return pessoaRepository.save(pessoa);
    }
    @Transactional
    public void excluir (Long pessoaId){
    pessoaRepository.deleteById(pessoaId);
    }
}

