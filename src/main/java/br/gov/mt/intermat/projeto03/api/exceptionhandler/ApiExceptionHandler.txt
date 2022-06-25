package br.gov.mt.intermat.projeto03.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.gov.mt.intermat.projeto03.domain.exception.NegocioException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                List<Problema.Campo> campos = new ArrayList<>();
                for(ObjectError error : ex.getBindingResult().getAllErrors()){
                    String nome = ((FieldError) error ).getField();
                    // String mensagem = error.getDefaultMessage();
                    String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
                    // String mensagem = Messages.getDefaultMessage(error, LocaleContextHolder.getLocale());
                    campos.add(new Problema.Campo(nome, mensagem ));
                }
                Problema problema = new Problema();
                problema.setStatus(status.value());
                problema.setDataHora(LocalDateTime.now());
                problema.setTitulo("um ou mais campos inválidos");
                problema.setCampos(campos);

            return handleExceptionInternal(ex, problema, headers, status, request);
    }
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
                Problema problema = new Problema();
                problema.setStatus(status.value());
                problema.setDataHora(LocalDateTime.now());
                problema.setTitulo(ex.getMessage());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }
}
