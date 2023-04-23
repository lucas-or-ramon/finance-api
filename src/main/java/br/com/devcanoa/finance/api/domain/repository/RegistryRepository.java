package br.com.devcanoa.finance.api.domain.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;

import java.util.List;
import java.util.Optional;

public interface RegistryRepository<E extends RegistryEntity> {

    List<E> getByDescription(String description);
    Optional<E> getById(String id);
    List<E> getByDate(int dateAsNumericValue);
    Optional<E> save(E entity);
    Optional<E> delete(String id);
}
