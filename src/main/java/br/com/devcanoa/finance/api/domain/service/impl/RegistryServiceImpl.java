package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RegistryEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.RecurrenceException;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import br.com.devcanoa.finance.api.domain.service.RegistryService;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class RegistryServiceImpl<E extends RegistryEntity> implements RegistryService {

    private final RegistryRepository<E> registryRepository;
    private final RegistryEntityMapper<E> mapper;
    private final CreditCardService creditCardService;

    public RegistryServiceImpl(RegistryRepository<E> registryRepository,
                               RegistryEntityMapper<E> mapper,
                               CreditCardService creditCardService) {
        this.mapper = mapper;
        this.registryRepository = registryRepository;
        this.creditCardService = creditCardService;
    }

    @Override
    public List<Registry> getByDescription(String description) {
        final var registries = registryRepository.getByDescription(description).stream().toList();
        final var creditCardIds = registries.stream()
                .filter(RegistryEntity::hasCreditCard)
                .collect(Collectors.groupingBy(RegistryEntity::getCreditCardId));

        final var creditCards = creditCardIds.keySet().stream()
                .map(ObjectId::toString)
                .map(creditCardService::getById)
                .toList();

        return creditCardIds.entrySet().stream()
                .map(entry -> {
                    final var creditCard = creditCards.stream()
                            .filter(card -> card.id().equals(entry.getKey().toString()))
                            .findFirst()
                            .orElseThrow(() -> new RegistryException("Credit Card Not Found"));

                    return entry.getValue().stream()
                            .map(registry -> registry.mapToDomain(creditCard))
                            .toList();
                })
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public D getById(final String id) {
        return registryRepository.getById(id)
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryNotFoundException("Registry Not Found"));
    }

    @Override
    public List<D> getByDate(final FinanceDate date) {
        return registryRepository.getByDate(date.getNumericValue()).stream()
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public D insert(final D registry) {
        if (!registry.getRecurrence().isValid()) {
            throw new RecurrenceException("Recurrent Registry Is Not Valid");
        }

        return registryRepository.save(mapper.mapToEntity(registry))
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryException("Registry Not Saved"));

    }

    @Override
    public D update(final D registry) {
        if (registry.getRecurrence().isValid()) {
            return registryRepository.save(mapper.mapToEntity(registry))
                    .map(mapper::mapToDomain)
                    .orElseThrow(() -> new RegistryException("Registry Not Updated"));
        }
        throw new RecurrenceException("Recurrent Registry Is Not Valid");
    }

    @Override
    public void delete(String id) {
        registryRepository.delete(id)
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryException("Registry Not Deleted"));
    }
}
