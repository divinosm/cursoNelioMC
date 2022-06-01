package br.gov.mt.intermat.projeto03.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Categoria;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    List<Categoria> findByNome(String nome);
    List<Categoria> findByNomeContaining(String nome);
    // Optional <Categoria> findByEmail(String email);
}

