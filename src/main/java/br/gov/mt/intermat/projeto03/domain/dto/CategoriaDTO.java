package br.gov.mt.intermat.projeto03.domain.dto;

import java.io.Serializable;

import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private Long id;
    private String nome;
    
    public CategoriaDTO (Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }
}
