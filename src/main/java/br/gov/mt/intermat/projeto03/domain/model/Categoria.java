package br.gov.mt.intermat.projeto03.domain.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //apenas propriedades implicitas
@Table (name = "categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    //@JsonManagedReference // no lado que vc quer que venham os objetos referenciados
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();
    // private List<Produto> produtos = new ArrayList<>();
    
    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Categoria() {
  
    }
 
   
}
