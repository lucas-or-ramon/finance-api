package br.com.devcanoa.finance.api.domain.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.CreditCardEntity;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {

    List<CreditCardEntity> listAll();
    Optional<CreditCardEntity> getById(final String id);
    Optional<CreditCardEntity> save(final CreditCardEntity entity);
    Optional<CreditCardEntity> delete(final String id);
}
