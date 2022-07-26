package br.gov.mt.intermat.projeto03.api.exceptionhandler;

import java.io.Serializable;

import lombok.Data;

@Data
public class FieldMessage implements Serializable{
    private static final long serialVersionUID = 1L;  
    
    private String fieldName;
    private String message;
    
    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public FieldMessage (){

    }
 
  
}
