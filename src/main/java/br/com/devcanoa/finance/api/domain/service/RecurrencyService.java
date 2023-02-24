package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;
import br.com.devcanoa.finance.api.domain.port.repository.RegistryRepository;

import java.util.Optional;

public class RecurrencyService<T extends Registry> {

    private final RegistryRepository<T> registryRepository;
    private final RegistryRecurrencyService<T> registryRecurrencyService;

    public RecurrencyService(RegistryRepository<T> registryRepository,
                             RegistryRecurrencyService<T> registryRecurrencyService) {
        this.registryRepository = registryRepository;
        this.registryRecurrencyService = registryRecurrencyService;
    }
}
