package br.gov.mt.intermat.projeto03.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Produto;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
    List<Produto> findByNome(String nome);
    List<Produto> findByNomeContaining(String nome);
    // Optional <Produto> findByEmail(String email);
}
