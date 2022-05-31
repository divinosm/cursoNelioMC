package br.gov.mt.intermat.projeto03.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Pessoa;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long>{
    List<Pessoa> findByNome(String nome);
    List<Pessoa> findByNomeContaining(String nome);
    Optional <Pessoa> findByEmail(String email);
}
