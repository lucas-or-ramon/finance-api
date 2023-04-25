package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.domain.model.Either;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.service.CategoryService;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import org.bson.types.ObjectId;

import java.util.List;

public class RegistryServiceImpl implements RegistryService {

    private final RegistryRepository registryRepository;
    private final CreditCardService creditCardService;
    private final CategoryService categoryService;

    public RegistryServiceImpl(RegistryRepository registryRepository,
                               CreditCardService creditCardService,
                               CategoryService categoryService) {
        this.registryRepository = registryRepository;
        this.creditCardService = creditCardService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Registry> getByDescription(String description) {
        return registryRepository.getByDescription(description).stream().toList();
    }

    @Override
    public Either<String, Registry> getById(final String id) {
        return registryRepository.getById(id)
                .map(Either::<String, Registry>success)
                .orElse(Either.failure("Registry Not Found"));
    }

    @Override
    public List<Registry> getByDate(final Request.FinanceDateDto date) {
        return registryRepository.getByDate(date.mapToDomain().getNumericValue()).stream().toList();
    }

    @Override
    public Either<String, Registry> insert(final Request.RegistryDto request) {
        return save(new ObjectId().toString(), request);

    }

    @Override
    public Either<String, Registry> update(final String id, final Request.RegistryDto request) {
        return save(id, request);
    }

    @Override
    public Either<String, Registry> delete(String id) {
        return registryRepository.delete(id)
                .map(Either::<String, Registry>success)
                .orElse(Either.failure("Registry Not Deleted"));

    }

    private Either<String, Registry> save(final String id, Request.RegistryDto request) {
        if (request.creditCardId() != null) {
            final var creditCard = creditCardService.getById(request.creditCardId());
            if (creditCard.isFailure()) {
                return Either.failure(creditCard.getFailure());
            }
        }

        final var category = categoryService.getByName(request.category());
        if (category.isFailure()) {
            return Either.failure(category.getFailure());
        }

        final var registry = Registry.builder()
                .id(id)
                .value(request.value())
                .category(request.category())
                .recurrence(request.recurrence().mapToDomain())
                .description(request.description())
                .creditCardId(request.creditCardId())
                .build();

        return registryRepository.save(registry)
                .map(Either::<String, Registry>success)
                .orElse(Either.failure("Registry Not Saved"));
    }
}
