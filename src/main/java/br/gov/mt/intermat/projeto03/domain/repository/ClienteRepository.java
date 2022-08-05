package br.gov.mt.intermat.projeto03.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.model.Cliente;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);
    @Transactional(readOnly = true)
    Cliente findByEmail (String email);
    // Optional <Cliente> findByEmail(String email);
}