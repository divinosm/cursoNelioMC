package br.gov.mt.intermat.projeto03.api.exceptionhandler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.mt.intermat.projeto03.domain.service.exceptions.DataIntegrityException;
import br.gov.mt.intermat.projeto03.domain.service.exceptions.ObjetcNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjetcNotFoundException.class)
    public ResponseEntity<StandardError> 
           objectNotFound(ObjetcNotFoundException e,  HttpServletRequest request){
                HttpStatus statusHTTP = HttpStatus.NOT_FOUND;
                Integer status = statusHTTP.value();
                LocalDateTime dataHora = LocalDateTime.now();
                String msgErro = e.getMessage();
                StandardError err = new StandardError(status, msgErro, dataHora);
            return ResponseEntity.status(statusHTTP).body(err);
    }
    
    @ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> 
           dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
            HttpStatus statusHTTP = HttpStatus.BAD_REQUEST;
            Integer status = statusHTTP.value();
            LocalDateTime dataHora = LocalDateTime.now();
            String msgErro = e.getMessage();
            StandardError err = new StandardError(status, msgErro, dataHora);
            System.out.println(err);
            return ResponseEntity.status(statusHTTP).body(err);
	}

    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> 
            validation(MethodArgumentNotValidException e, HttpServletRequest request) {
            HttpStatus statusHTTP = HttpStatus.BAD_REQUEST;
            Integer status = statusHTTP.value();
            LocalDateTime dataHora = LocalDateTime.now();
            String msgErro = "Erro de validação"; //e.getMessage();
            // String msgErro = e.getMessage();
            ValidationError err = new ValidationError(status, msgErro, dataHora);
            // StandardError err = new StandardError(status, msgErro, dataHora);
            for (FieldError x:  e.getBindingResult().getFieldErrors()){
                err.addErro(x.getField(), x.getDefaultMessage());
            }
            // System.out.println( "*********************************");
            // System.out.println( e.getBindingResult().getFieldErrors());
            return ResponseEntity.status(statusHTTP).body(err);
	}
}
