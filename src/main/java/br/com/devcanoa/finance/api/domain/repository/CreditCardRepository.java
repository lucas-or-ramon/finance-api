package br.com.devcanoa.finance.api.domain.repository;

import br.com.devcanoa.finance.api.domain.model.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {

    List<CreditCard> listAll();
    Optional<CreditCard> getById(final String id);
    Optional<CreditCard> save(final CreditCard entity);
    Optional<CreditCard> delete(final String id);
}
