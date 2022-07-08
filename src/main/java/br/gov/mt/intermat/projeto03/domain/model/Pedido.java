package br.gov.mt.intermat.projeto03.domain.model;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table (name = "pedido")
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;
    
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "enderecoEntrega_id")
    private Endereco enderecoEntrega;

    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();


    public Pedido(Long id, Date instante,  
                  Cliente cliente, Endereco enderecoEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
    }
    public Pedido() {
  
    }
}
