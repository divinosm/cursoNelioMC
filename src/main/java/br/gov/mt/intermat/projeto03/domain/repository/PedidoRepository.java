package br.gov.mt.intermat.projeto03.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.Pedido;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{
    //List<Pedido> findByNome(String nome);
    //List<Pedido> findByNomeContaining(String nome);
    // Optional <Pedido> findByEmail(String email);
}