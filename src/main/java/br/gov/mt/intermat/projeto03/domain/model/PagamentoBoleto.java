package br.gov.mt.intermat.projeto03.domain.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import br.gov.mt.intermat.projeto03.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper=false)
@Table (name = "pagamentoBoleto")
public class PagamentoBoleto extends Pagamento   {
    private static final long serialVersionUID = 1L;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;
    
    
    public PagamentoBoleto(Long id, EstadoPagamento estadoPagamento, 
                 Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estadoPagamento, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
    public PagamentoBoleto() {
     
    }
}