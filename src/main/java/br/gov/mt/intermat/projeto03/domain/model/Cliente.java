package br.gov.mt.intermat.projeto03.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.gov.mt.intermat.projeto03.domain.enums.TipoCliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // apenas propriedades implicitas
@Table(name = "cliente")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private String email;
    private String cpfoucnpj;
    @Column(name="tipocliente")
    private Integer tipoClienteInt;
    //private TipoCliente tipoCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

    // set não aceita repetição
    @ElementCollection
    @CollectionTable(name="telefone")
    private Set<String> telefones = new HashSet<>(); 

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    // tratar enum
    public  TipoCliente getTipoCliente(){
        return TipoCliente.toEnum(tipoClienteInt);
    }
    public  void setTipoCliente (TipoCliente tipoCliente){
        this.tipoClienteInt = tipoCliente.getCodigo();
    }

     

    public Cliente(Long id, String nome, String email, String cpfoucnpj, 
                   TipoCliente tipoCliente){
          this.id = id;
          this.nome = nome;
          this.email = email;
          this.cpfoucnpj = cpfoucnpj;
          this.tipoClienteInt = (tipoCliente == null) ? null : tipoCliente.getCodigo();
      }

      
    public Cliente() {
    }

}
