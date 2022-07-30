package br.gov.mt.intermat.projeto03.domain.service.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.gov.mt.intermat.projeto03.api.exceptionhandler.FieldMessage;
import br.gov.mt.intermat.projeto03.domain.dto.ClienteNewDto;
import br.gov.mt.intermat.projeto03.domain.enums.TipoCliente;
import br.gov.mt.intermat.projeto03.domain.service.validation.utils.Br;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        
        if (objDto.getTipoClienteInt().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !Br.isValidCPF(objDto.getCpfoucnpj())) {
			list.add(new FieldMessage("cpfoucnpj", "CPF inválido"));
		}

		if (objDto.getTipoClienteInt().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !Br.isValidCNPJ(objDto.getCpfoucnpj())) {
			list.add(new FieldMessage("cpfoucnpj", "CNPJ inválido"));
		}
        // depois de fazer todas as críticas, vou transferir a lista criada para o gerenciamento do framework
        // 
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
