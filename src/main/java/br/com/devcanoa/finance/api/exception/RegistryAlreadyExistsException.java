package br.com.devcanoa.finance.api.exception;

public class RegistryAlreadyExistsException extends RuntimeException {

    public RegistryAlreadyExistsException(String message) {
        super(message);
    }
}
