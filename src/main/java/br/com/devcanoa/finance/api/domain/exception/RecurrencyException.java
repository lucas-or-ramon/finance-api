package br.com.devcanoa.finance.api.domain.exception;

public class RecurrencyException extends FinanceException {

    public RecurrencyException(String message) {
        super(message);
    }

    public RecurrencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
