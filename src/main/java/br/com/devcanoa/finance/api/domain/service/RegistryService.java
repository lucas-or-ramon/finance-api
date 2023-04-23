package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Registry;

import java.util.List;

public interface RegistryService {

    List<Registry> getByDescription(final String description);

    Registry getById(final String id);

    List<Registry> getByDate(final FinanceDate date);

    Registry insert(final Registry registry);

    Registry update(final Registry registry);

    void delete(final String id);
}
