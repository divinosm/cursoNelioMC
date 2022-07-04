package br.gov.mt.intermat.projeto03.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Pagamento;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{
    //List<Pagamento> findByNome(String nome);
    //List<Pagamento> findByNomeContaining(String nome);
    // Optional <Pagamento> findByEmail(String email);
}
