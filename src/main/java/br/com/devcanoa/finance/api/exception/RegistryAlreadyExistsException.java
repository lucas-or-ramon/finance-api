package br.com.devcanoa.finance.api.exception;

public class RegistryAlreadyExistsException extends FinanceException {

    public RegistryAlreadyExistsException(final String message) {
        super(message);
    }
}
