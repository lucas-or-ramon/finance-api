package br.com.devcanoa.finance.api.domain.repository;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.domain.model.Registry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegistryRepository<T extends RegistryEntity> {

    List<T> getByDescription(String description);
    Optional<T> getById(String id);
    List<T> getByDate(LocalDate date);
    Optional<T> save(T entity);
    Optional<T> delete(String id);
}
