package br.gov.mt.intermat.projeto03.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Cidade;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long>{
    List<Cidade> findByNome(String nome);
    List<Cidade> findByNomeContaining(String nome);
    // Optional <Cidade> findByEmail(String email);
}