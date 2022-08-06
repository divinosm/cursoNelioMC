package br.gov.mt.intermat.projeto03.domain.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import br.gov.mt.intermat.projeto03.domain.model.Produto;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
    List<Produto> findByNome(String nome);
    List<Produto> findByNomeContaining(String nome);
    // Optional <Produto> findByEmail(String email);
    //
    // para evitar criar uma class, a anotação @query permite que eu 
    // pegue em tempo de execução e transfira os valores anotados em @param
    // e transfira para a @query
    // o comando abaixo não precisa de query e param, pois o spring data faria
    // Page<Produto> findDistinctByNomeContainingAndCategoriasIn ( String nome, List<Categoria> categorias, Pageable pageRequest);
    // Por ser consulta, vou usar o transaction para não prender registros
    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn (@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
    
}
