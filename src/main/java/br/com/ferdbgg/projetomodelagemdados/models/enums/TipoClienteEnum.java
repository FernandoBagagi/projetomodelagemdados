package br.com.ferdbgg.projetomodelagemdados.models.enums;

public enum TipoClienteEnum {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private final Integer codigo;
    private final String nome;

    private TipoClienteEnum(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public static TipoClienteEnum fromInteger(Integer codigo) {
        for(TipoClienteEnum tipoCliente :  TipoClienteEnum.values()) {
            if(tipoCliente.getCodigo().equals(codigo) ) {
                return tipoCliente;
            }
        }
        return null;
    }

}