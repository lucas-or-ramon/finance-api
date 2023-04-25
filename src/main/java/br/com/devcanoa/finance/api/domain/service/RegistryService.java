package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.domain.model.Either;
import br.com.devcanoa.finance.api.domain.model.Registry;

import java.util.List;

public interface RegistryService {

    List<Registry> getByDescription(final String description);

    Either<String, Registry> getById(final String id);

    List<Registry> getByDate(final Request.FinanceDateDto date);

    Either<String, Registry> insert(final Request.RegistryDto request);

    Either<String, Registry> update(final String id, final Request.RegistryDto request);

    Either<String, Registry> delete(final String id);
}
