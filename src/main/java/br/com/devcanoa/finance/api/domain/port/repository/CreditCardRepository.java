package br.com.devcanoa.finance.api.domain.port.repository;

import br.com.devcanoa.finance.api.infra.adapter.entity.CreditCard;
import org.bson.types.ObjectId;

import java.util.List;

public interface CreditCardRepository {

    List<CreditCard> listAll();
    CreditCard getById(final ObjectId id);
    void save(final CreditCard creditCard);
    void delete(final ObjectId id);
}
