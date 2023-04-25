package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.domain.model.Either;
import br.com.devcanoa.finance.api.domain.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    @Override
    public Either<String, String> getByName(String name) {
        return Either.success(name);
    }
}
