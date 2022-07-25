package br.com.devcanoa.finance.api.exception;

public class CategoryNotFoundException extends RegistryNotFoundException {

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
