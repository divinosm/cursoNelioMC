package br.gov.mt.intermat.projeto03.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.gov.mt.intermat.projeto03.domain.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDto implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private Long id;
    @NotEmpty (message = "preenchimento obrigatório")
    @Length(min=5, max=80, message = "o tamanho deve ser entre 5 a 80 caracteres")
    private String nome;
    
    public CategoriaDto (Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    public CategoriaDto() {
	}
}
