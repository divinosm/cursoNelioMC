package br.gov.mt.intermat.projeto03.domain.exception;

public class NegocioException  extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NegocioException (String message){
        super(message);
    }
}
