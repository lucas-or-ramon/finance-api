package br.com.devcanoa.finance.api.domain.exception;

public class FinanceException extends RuntimeException {

    public FinanceException(final String message) {
        super(message);
    }

    public FinanceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
