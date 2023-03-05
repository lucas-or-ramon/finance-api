package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.domain.model.CreditCard;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> listAll();
    CreditCard getById(final String id);
    void insert(final CreditCard creditCard);
    void update(final CreditCard creditCard);
    void delete(final String id);
}
