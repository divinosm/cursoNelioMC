package br.gov.mt.intermat.projeto03.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Estado;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long>{
    List<Estado> findByNome(String nome);
    List<Estado> findByNomeContaining(String nome);
    // Optional <Estado> findByEmail(String email);
}