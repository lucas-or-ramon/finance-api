package br.com.devcanoa.finance.domain.exception;

public class RegistryNotFoundException extends RuntimeException {

    public RegistryNotFoundException(String message) {
        super(message);
    }
}
