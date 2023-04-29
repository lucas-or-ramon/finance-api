package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.domain.model.Either;
import br.com.devcanoa.finance.api.domain.repository.CategoryRepository;
import br.com.devcanoa.finance.api.domain.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Either<String, String> handle(String name) {
        return repository.getByName(name)
                .map(Either::<String, String>success)
                .orElseGet(() -> {
                    final var id = repository.save(name);
                    return Either.success(id);
                });
    }
}
