package br.gov.mt.intermat.projeto03.domain.model;
import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //apenas propriedades implicitas
@Table (name = "itemPedido")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @EmbeddedId
    @EqualsAndHashCode.Include
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private ItemPedidoPk id = new ItemPedidoPk();
    
    private Double desconto;
    private Integer quantidade;
    private Double preco;
    // quando coloco o get na frente, o mesmo será
    // reconhecido pelo JSON e serializado. Vai aparecer
    // no corpo do postman.
    public Double getSubTotal(){
        return ((preco - desconto)*quantidade);
    }


    //@JsonIgnore
    //@JsonManagedReference // no lado que vc quer que venham os objetos referenciados

    // @ManyToMany(fetch = FetchType.LAZY, mappedBy = "itens")
    // private List<Produto> produtos = new ArrayList<>();
    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

    public Produto getProduto(){
        return id.getProduto();
    }
    public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
    
    public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    } 
    public ItemPedido() {
    } 
   
}