package br.gov.mt.intermat.projeto03.domain.enums;


public enum EstadoPagamento {
    PENDENTE(1,"Pendente"),
    QUITADO(2,"Quitado"),
    PARCELADO(3,"Parcelado");
    
     

    private int codigo;
    private String descricao;
     
    private EstadoPagamento (int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo (){
        
          return codigo;
    }
    public String getDescricao (){
        return descricao;
    }
    // static - operação deve ser executada mesmo sem instanciar cliente
    public static EstadoPagamento toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }
        for (EstadoPagamento x:EstadoPagamento.values()){
            if (codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("id invalido: "+codigo);
        
    }
}