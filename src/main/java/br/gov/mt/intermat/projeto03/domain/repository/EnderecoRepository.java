package br.gov.mt.intermat.projeto03.domain.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Endereco;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
   // List<Endereco> findByNome(String nome);
    //List<Endereco> findByNomeContaining(String nome);
    // Optional <Endereco> findByEmail(String email);
}
