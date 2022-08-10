package br.gov.mt.intermat.projeto03.domain.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.gov.mt.intermat.projeto03.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Table (name = "pagamentoCartao")
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento   {
    private static final long serialVersionUID = 1L;
 
    private Integer numeroParcelas;
     

 
    public PagamentoCartao(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public PagamentoCartao() {
  
    }
}