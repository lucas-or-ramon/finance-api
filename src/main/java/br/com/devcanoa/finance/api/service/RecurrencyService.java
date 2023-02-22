package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.model.Registry;
import br.com.devcanoa.finance.api.repository.MongoRepository;

import java.util.Optional;

public class RecurrencyService<T extends Registry> {

    private final MongoRepository<T> mongoRepository;
    private final RegistryRecurrencyService<T> registryRecurrencyService;

    public RecurrencyService(MongoRepository<T> mongoRepository,
                             RegistryRecurrencyService<T> registryRecurrencyService) {
        this.mongoRepository = mongoRepository;
        this.registryRecurrencyService = registryRecurrencyService;
    }

    public Optional<T> insertWithRecurrency(T registry) {
        return registryRecurrencyService.createRecurrency(registry).parallelStream()
                .map(mongoRepository::insert)
                .flatMap(Optional::stream)
                .filter(r -> r.getId().equals(registry.getId()))
                .findFirst();
    }
}
