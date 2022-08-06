package br.gov.mt.intermat.projeto03.domain.dto;

import java.io.Serializable;

import br.gov.mt.intermat.projeto03.domain.model.Produto;
import lombok.Data;

@Data
public class ProdutoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Double preco;
    
    public ProdutoDto (Produto obj){
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
    }

    public ProdutoDto() {
	}
}
