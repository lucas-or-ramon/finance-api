package br.com.devcanoa.finance.api.domain.exception;

public class RecurrenceException extends FinanceException {

    public RecurrenceException(String message) {
        super(message);
    }

    public RecurrenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
