package br.com.devcanoa.finance.api.exception;

public class RegistryNotFoundException extends RuntimeException {

    public RegistryNotFoundException(String message) {
        super(message);
    }
}
