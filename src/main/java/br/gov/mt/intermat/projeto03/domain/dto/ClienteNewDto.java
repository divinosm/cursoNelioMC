package br.gov.mt.intermat.projeto03.domain.dto;
import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;

@Data
public class ClienteNewDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    private String nome;
    private String email;
    private String cpfoucnpj;
    @Column(name="tipocliente")
    private Integer tipoClienteInt;

    @Column(nullable = false)
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Long cidadeId;
}
