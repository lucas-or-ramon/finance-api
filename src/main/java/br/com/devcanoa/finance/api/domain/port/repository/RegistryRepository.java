package br.com.devcanoa.finance.api.domain.port.repository;

import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RegistryRepository<T extends Registry> {

    List<T> getByDescription(String description);
    T getById(ObjectId id);
    List<T> getByDate(LocalDate date);
    boolean exist(final T registry);
    void save(T registry);
    void delete(ObjectId id);
}
