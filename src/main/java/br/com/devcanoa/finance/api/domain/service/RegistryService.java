package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;

import java.util.List;

public interface RegistryService<T extends Registry> {

    List<T> getByDescription(final String description);
    T getById(final String id);
    List<T> getByDate(final FinanceDate date);
    T insert(final T registry);
    T update(final T registry);
    void delete(final String id);
}
