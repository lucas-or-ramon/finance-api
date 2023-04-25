package br.com.devcanoa.finance.api.domain.repository;

import br.com.devcanoa.finance.api.domain.model.Registry;

import java.util.List;
import java.util.Optional;

public interface RegistryRepository {

    List<Registry> getByDescription(String description);

    Optional<Registry> getById(String id);

    List<Registry> getByDate(int dateAsNumericValue);

    Optional<Registry> save(Registry entity);

    Optional<Registry> delete(String id);
}
