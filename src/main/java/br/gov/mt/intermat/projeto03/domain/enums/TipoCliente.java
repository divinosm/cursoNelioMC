package br.gov.mt.intermat.projeto03.domain.enums;


public enum TipoCliente {
    PESSOAFISICA(1,"Pessoa física"),
    PESSOAJURIDICA(2,"Pessoa jurídica");

    private int codigo;
    private String descricao;
     
    private TipoCliente (int codigo, String descricao){
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
    public static TipoCliente toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }
        for (TipoCliente x:TipoCliente.values()){
            if (codigo.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("id invalido: "+codigo);
        
    }
}
