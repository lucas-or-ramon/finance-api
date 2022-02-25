package br.com.devcanoa.finance.domain.exception;

public class CategoryNotFoundException extends RegistryNotFoundException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
