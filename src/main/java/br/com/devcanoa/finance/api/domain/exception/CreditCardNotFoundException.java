package br.com.devcanoa.finance.api.domain.exception;

public class CreditCardNotFoundException extends FinanceException {

    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
