package br.gov.mt.intermat.projeto03.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.intermat.projeto03.domain.model.ItemPedido;
//
//classe responsável por interagir com o banco de dados
//
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long>{
   // List<ItemPedido> findByNome(String nome);
   // List<ItemPedido> findByNomeContaining(String nome);
    // Optional <ItemPedido> findByEmail(String email);
}