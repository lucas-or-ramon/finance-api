package br.com.devcanoa.finance.api.domain.repository;

import java.util.Optional;

public interface CategoryRepository {

    Optional<String> getByName(final String name);
    String save(final String name);
}
