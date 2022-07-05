package br.gov.mt.intermat.projeto03.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Embeddable // esta classe será incorporada por outra, não precisa criar tabela para ela
@Data
public class ItemPedidoPk implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    
}
