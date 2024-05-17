package br.com.ferdbgg.projetomodelagemdados.services;

public class ObjetoNaoEncontradoException extends RuntimeException{

    public ObjetoNaoEncontradoException(Integer id, Class<?> classe) {
        super(String.format("Não foi encontrado nenhum objeto da classe %s com id %d.", classe.getSimpleName(), id));
    }

}
