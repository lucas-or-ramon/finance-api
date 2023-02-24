package br.com.devcanoa.finance.api.domain.service;

import br.com.devcanoa.finance.api.domain.recurrency.FixedRecurrency;
import br.com.devcanoa.finance.api.domain.recurrency.OnceRecurrency;
import br.com.devcanoa.finance.api.domain.recurrency.RecurrencyRules;
import br.com.devcanoa.finance.api.domain.recurrency.RecurrencyType;
import br.com.devcanoa.finance.api.domain.adapter.mapper.registry.RegistryMapper;
import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;

import java.util.HashMap;
import java.util.List;

import static br.com.devcanoa.finance.api.domain.recurrency.RecurrencyType.*;

public class RegistryRecurrencyService<T extends Registry> {

    private final RegistryMapper<T> registryMapper;
    private final HashMap<RecurrencyType, RecurrencyRules<T>> recurrencyRules;

    public RegistryRecurrencyService(RegistryMapper<T> registryMapper) {
        this.registryMapper = registryMapper;
        this.recurrencyRules = new HashMap<>();
        this.recurrencyRules.put(FIXED, new FixedRecurrency<>(this.registryMapper));
        this.recurrencyRules.put(PARCEL, null);
        this.recurrencyRules.put(ONCE, new OnceRecurrency<>());
    }

    public List<T> createRecurrency(T registry) {
        return recurrencyRules.get(registry.getRecurrency().type()).creates(registry);
    }
}
