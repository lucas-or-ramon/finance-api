package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.domain.exception.RegistryException;
import br.com.devcanoa.finance.api.domain.exception.RegistryNotFoundException;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.repository.RegistryRepository;
import br.com.devcanoa.finance.api.domain.service.RegistryService;

import java.time.LocalDate;
import java.util.List;

public class RegistryServiceImpl<T extends Registry> implements RegistryService<T> {

    private final RegistryRepository<T> repository;

    public RegistryServiceImpl(RegistryRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getByDescription(String description) {
        return repository.getByDescription(description);
    }

    @Override
    public T getById(final String id) {
        return repository.getById(id).orElseThrow(() -> new RegistryNotFoundException("Registry Not Found"));
    }

    @Override
    public List<T> getByDate(LocalDate date) {
        return repository.getByDate(date);
    }

    @Override
    public void insert(final T registry) {
        repository.save(registry).orElseThrow(() -> new RegistryException("Registry Not Saved"));
    }

    @Override
    public void update(final T request) {
        repository.save(request).orElseThrow(() -> new RegistryException("Registry Not Updated"));
    }

    @Override
    public void delete(String id) {
        repository.delete(id).orElseThrow(() -> new RegistryException("Registry Not Deleted"));
    }
}
