package br.com.devcanoa.finance.api.domain.exception;

public class CreditCardNotFoundException extends CreditCardException {

    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
