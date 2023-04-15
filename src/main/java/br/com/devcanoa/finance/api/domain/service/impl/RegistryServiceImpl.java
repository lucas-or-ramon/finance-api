package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RecurrencyEntityMapper;
import br.com.devcanoa.finance.api.adapter.outbound.mapper.RegistryEntityMapper;
import br.com.devcanoa.finance.api.domain.exception.RecurrencyException;
import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.repository.RecurrencyRepository;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.service.RegistryService;

import java.time.LocalDate;
import java.util.List;

import static br.com.devcanoa.finance.api.adapter.outbound.mapper.RecurrencyEntityMapper.mapToEntity;

public class RegistryServiceImpl<T extends Registry, R extends RegistryEntity> implements RegistryService<T> {

    private final RegistryRepository<R> repository;
    private final RecurrencyRepository recurrencyRepository;
    private final RegistryEntityMapper<R, T> mapper;

    public RegistryServiceImpl(RegistryRepository<R> repository, RecurrencyRepository recurrencyRepository, RegistryEntityMapper<R, T> mapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.recurrencyRepository = recurrencyRepository;
    }

    @Override
    public List<T> getByDescription(String description) {
        return repository.getByDescription(description).stream().map(mapper::mapToDomain).toList();
    }

    @Override
    public T getById(final String id) {
        return repository.getById(id)
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryNotFoundException("Registry Not Found"));
    }

    @Override
    public List<T> getByDate(LocalDate date) {
        return repository.getByDate(date).stream().map(mapper::mapToDomain).toList();
    }

    @Override
    public void insert(final T registry) {
        recurrencyRepository.save(mapToEntity(registry.getRecurrency()))
                .map(RecurrencyEntityMapper::mapToDomain)
                .orElseThrow(() -> new RecurrencyException("Recurrency Not Saved"));

        repository.save(mapper.mapToEntity(registry))
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryException("Registry Not Saved"));
    }

    @Override
    public void update(final T request) {
        repository.save(mapper.mapToEntity(request))
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryException("Registry Not Updated"));
    }

    @Override
    public void delete(String id) {
        repository.delete(id)
                .map(mapper::mapToDomain)
                .orElseThrow(() -> new RegistryException("Registry Not Deleted"));
    }
}
