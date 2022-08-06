package br.gov.mt.intermat.projeto03.domain.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.gov.mt.intermat.projeto03.api.exceptionhandler.FieldMessage;
import br.gov.mt.intermat.projeto03.domain.dto.ClienteDto;
import br.gov.mt.intermat.projeto03.domain.model.Cliente;
import br.gov.mt.intermat.projeto03.domain.repository.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, 
                                    ClienteDto> {
    
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private HttpServletRequest request;
    
    
    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        //
        // vou pegar o id do parâmetro da uri
        //
        @SuppressWarnings("unchecked")
        Map<String,String> map = (Map<String,String>) 
               request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("clienteId"));


        // inclua os testes aqui, inserindo erros na lista
        
        Cliente clitst = clienteRepository.findByEmail(objDto.getEmail());
        if (clitst != null && !clitst.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "email já cadastrado"));
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
