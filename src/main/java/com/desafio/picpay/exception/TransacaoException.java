package com.desafio.picpay.exception;

public class TransacaoException extends RuntimeException {

    public TransacaoException(String mensagem) {
        super(mensagem);
    }
}
