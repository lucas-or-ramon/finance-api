package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.Either;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> listAll();

    Either<String, CreditCard> getById(final String id);

    Either<String, CreditCard> insert(final Request.CreditCardDto creditCard);

    Either<String, CreditCard> update(final String id, final Request.CreditCardDto creditCard);

    Either<String, CreditCard> delete(final String id);
}
