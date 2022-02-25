package br.com.devcanoa.finance.domain.exception;

public class RegistryAlreadyExistsException extends RuntimeException {

    public RegistryAlreadyExistsException(String message) {
        super(message);
    }
}
