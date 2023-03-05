package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.domain.exception.CreditCardException;
import br.com.devcanoa.finance.api.domain.exception.CreditCardNotFoundException;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.repository.CreditCardRepository;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;

import java.util.List;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository repository;

    public CreditCardServiceImpl(CreditCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CreditCard> listAll() {
        return repository.listAll();
    }

    @Override
    public CreditCard getById(String id) {
        return repository.getById(id).orElseThrow(() -> new CreditCardNotFoundException("Credit Card Not Found"));
    }

    @Override
    public void insert(CreditCard creditCard) {
        repository.save(creditCard).orElseThrow(() -> new CreditCardException("Credit Card Not Saved"));
    }

    @Override
    public void update(CreditCard creditCard) {
        repository.save(creditCard).orElseThrow(() -> new CreditCardException("Credit Card Not Updated"));
    }

    @Override
    public void delete(String id) {
        repository.delete(id).orElseThrow(() -> new CreditCardException("Credit Card Not Deleted"));
    }
}
