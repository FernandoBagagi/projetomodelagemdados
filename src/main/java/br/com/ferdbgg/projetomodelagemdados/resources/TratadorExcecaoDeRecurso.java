package br.com.ferdbgg.projetomodelagemdados.resources;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ferdbgg.projetomodelagemdados.models.ErroPadrao;
import br.com.ferdbgg.projetomodelagemdados.services.ObjetoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class TratadorExcecaoDeRecurso {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontradoException exception, HttpServletRequest request) {
        final HttpStatus status = HttpStatus.NOT_FOUND;
        final ErroPadrao erro = new ErroPadrao();
        erro.setStatus(status.value());
        erro.setMensagem(exception.getMessage());
        erro.setInstante(Instant.now());
        return ResponseEntity.status(status).body(erro);
    }
    
}
