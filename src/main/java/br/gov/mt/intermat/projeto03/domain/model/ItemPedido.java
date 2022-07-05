package br.gov.mt.intermat.projeto03.domain.model;
import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //apenas propriedades implicitas
@Table (name = "itemPedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @EqualsAndHashCode.Include
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private ItemPedidoPk id = new ItemPedidoPk();
    
    private Double desconto;
    private Integer quantidade;
    private Double preço;
    //@JsonIgnore
    //@JsonManagedReference // no lado que vc quer que venham os objetos referenciados

    // @ManyToMany(fetch = FetchType.LAZY, mappedBy = "itens")
    // private List<Produto> produtos = new ArrayList<>();

    public Pedido getPedido(){
        return id.getPedido();
    }
    public Produto getProduto(){
        return id.getProduto();
    }
    
    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preço) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preço = preço;
    } 
    public ItemPedido() {
    } 
   
}