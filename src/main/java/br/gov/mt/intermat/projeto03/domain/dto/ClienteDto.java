package br.gov.mt.intermat.projeto03.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.gov.mt.intermat.projeto03.domain.model.Cliente;
import br.gov.mt.intermat.projeto03.domain.service.validation.ClienteUpdate;
import lombok.Data;
// dto - dados básicos para atualizar, deletar e listar os clientes
// vamos supor que cpf não é alterado, não incluo os objetos relacionados
// logo não entra no DTO.
@ClienteUpdate
@Data
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = 1L;
 
    private Long id;
    @NotEmpty (message = "preenchimento obrigatório")
    @Length(min=5, max=120, message = "o tamanho deve ser entre 5 a 120 caracteres")
    private String nome;
    @NotEmpty (message = "preenchimento obrigatório")
    @Email (message = "email inválido")
    private String email;
    
    public ClienteDto (Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

    public ClienteDto() {
	}
}