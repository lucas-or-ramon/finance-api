package br.com.devcanoa.finance.api.domain.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RecurrencyEntity;

import java.util.Optional;

public interface RecurrencyRepository {

    Optional<RecurrencyEntity> getById(String id);
    Optional<RecurrencyEntity> save(RecurrencyEntity entity);
    Optional<RecurrencyEntity> delete(String id);
}
