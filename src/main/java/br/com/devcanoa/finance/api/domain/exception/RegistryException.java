package br.com.devcanoa.finance.api.domain.exception;

public class RegistryException extends FinanceException {

    public RegistryException(String message) {
        super(message);
    }

    public RegistryException(String message, Throwable cause) {
        super(message, cause);
    }
}
