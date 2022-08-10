package br.gov.mt.intermat.projeto03.domain.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.gov.mt.intermat.projeto03.domain.enums.EstadoPagamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// posso implementar 1 para 1 de duas formas
// joined: cria várias tabelas para cada filho
// singletable: cria uma única tabela e coloca null nos campos não usados
//
@Inheritance(strategy = InheritanceType.JOINED)
@Table (name = "pagamento")
//abstract, não consigo instanciar. Se quiser devo 
//instanciar uma subclasse
//
// a classe pagamento vai ter um campo adicional chamado type
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @EqualsAndHashCode.Include
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // por ser relacionamento 1 para 1, recebe a chave do pedido
   // não precisa ser incrementado aqui.
   //
    private Long id;
    private Integer estadoPagamentoInt;
    
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;


    // tratar enum
    public  EstadoPagamento getEstadoPagamento(){
        return EstadoPagamento.toEnum(estadoPagamentoInt);
    }
    public  void setEstadoPagamento (EstadoPagamento estadoPagamento){
        this.estadoPagamentoInt = estadoPagamento.getCodigo();
    }

    public Pagamento(Long id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.estadoPagamentoInt = (estadoPagamento==null) ? null : estadoPagamento.getCodigo();
        this.pedido = pedido;
    }
    public Pagamento() {
  
    }
}
