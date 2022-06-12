package br.gov.mt.intermat.projeto03.domain.service.exceptions;

public class ObjetcNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

     

    public ObjetcNotFoundException (String message){
        super(message);
    }
    public ObjetcNotFoundException (String message, Throwable causa){
        super(message,causa);
    }
}
