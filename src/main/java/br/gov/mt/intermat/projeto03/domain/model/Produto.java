package br.gov.mt.intermat.projeto03.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //apenas propriedades implicitas
@Table (name = "produto")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private double preco;
    // @JsonBackReference // do outro lado já foi passado os campos do relacionamento
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn (name = "produto_id"),
            inverseJoinColumns = @JoinColumn (name="categoria_id"))
    private List <Categoria> categorias;

    //private List <Categoria> categorias = new ArrayList<>();
}
