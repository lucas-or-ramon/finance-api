package br.com.devcanoa.finance.api.domain.exception;

public class CreditCardAlreadyExistException extends CreditCardException {

    public CreditCardAlreadyExistException(String message) {
        super(message);
    }
}
