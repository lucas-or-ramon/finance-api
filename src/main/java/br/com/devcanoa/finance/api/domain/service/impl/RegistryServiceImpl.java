package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RegistryEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.RecurrenceException;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.service.RegistryService;

import java.util.List;

public class RegistryServiceImpl<T extends Registry, R extends RegistryEntity> implements RegistryService<T> {

    private final RegistryRepository<R> registryRepository;
    private final RegistryEntityMapper<R, T> mapper;

    public RegistryServiceImpl(RegistryRepository<R> registryRepository, RegistryEntityMapper<R, T> mapper) {
        this.mapper = mapper;
        this.registryRepository = registryRepository;
    }

    @Override
    public List<T> getByDescription(String description) {
        return registryRepository.getByDescription(description).stream()
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public T getById(final String id) {
        return registryRepository.getById(id)
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryNotFoundException("Registry Not Found"));
    }

    @Override
    public List<T> getByDate(final FinanceDate date) {
        return registryRepository.getByDate(date).stream()
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public T insert(final T registry) {
        if (!registry.getRecurrence().isValid()) {
            throw new RecurrenceException("Recurrent Registry Is Not Valid");
        }

        return registryRepository.save(mapper.mapToEntity(registry))
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryException("Registry Not Saved"));

    }

    @Override
    public T update(final T registry) {
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
