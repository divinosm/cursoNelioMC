package br.gov.mt.intermat.projeto03.domain.dto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.gov.mt.intermat.projeto03.domain.service.validation.ClienteInsert;
import lombok.Data;

@Data
@ClienteInsert
public class ClienteNewDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    @NotEmpty (message = "preenchimento obrigatório")
    @Length(min=5, max=120, message = "o tamanho deve ser entre 5 a 120 caracteres")
    private String nome;
    @NotEmpty (message = "preenchimento obrigatório")
    @Email (message = "email inválido")
    private String email;
    @NotEmpty (message = "preenchimento obrigatório")   
    private String cpfoucnpj;
    @Column(name="tipocliente")
    private Integer tipoClienteInt;

    @Column(nullable = false)
    @NotEmpty (message = "preenchimento obrigatório")
    private String logradouro;
    @NotEmpty (message = "preenchimento obrigatório")
    private String numero;
    private String complemento;
    private String bairro;
    @NotEmpty (message = "preenchimento obrigatório")
    private String cep;

    @NotEmpty (message = "preenchimento obrigatório")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;
}
