package br.com.ferdbgg.projetomodelagemdados.models.enums;

public interface EnumPadrao <T extends Enum<T> & EnumPadrao<T>> {

    public Integer getCodigo();

    public static <T extends Enum<T> & EnumPadrao<T>> T fromInteger(Class<T> enumType, Integer codigo) {
        for (T enumConstant : enumType.getEnumConstants()) {
            if (enumConstant.getCodigo().equals(codigo)) {
                return enumConstant;
            }
        }
        return null;
    }
    
}
