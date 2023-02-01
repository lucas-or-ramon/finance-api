package br.com.devcanoa.finance.api.exception;

public class RegistryNotFoundException extends FinanceException {

    public RegistryNotFoundException(final String message) {
        super(message);
    }
}
