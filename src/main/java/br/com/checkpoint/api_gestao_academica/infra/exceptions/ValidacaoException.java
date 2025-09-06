package br.com.checkpoint.api_gestao_academica.infra.exceptions;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String message) {
        super(message);
    }
}
