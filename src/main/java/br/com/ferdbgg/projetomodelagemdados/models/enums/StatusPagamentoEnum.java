package br.com.ferdbgg.projetomodelagemdados.models.enums;

public enum StatusPagamentoEnum implements EnumPadrao<StatusPagamentoEnum> {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(2, "Cancelado");

    private final Integer codigo;
    private final String nome;

    private StatusPagamentoEnum(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    @Override
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

    public static StatusPagamentoEnum fromInteger(Integer codigo) {
        return EnumPadrao.fromInteger(StatusPagamentoEnum.class, codigo);
    }

}
