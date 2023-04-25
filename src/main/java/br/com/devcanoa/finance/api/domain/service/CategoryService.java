package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.domain.model.Either;

public interface CategoryService {

    Either<String, String> getByName(final String name);
}
