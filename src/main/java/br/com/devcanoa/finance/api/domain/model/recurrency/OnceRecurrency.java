package br.com.devcanoa.finance.api.domain.model.recurrency;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;

import java.util.List;

public class OnceRecurrency<T extends RegistryEntity> implements RecurrencyRules<T> {

    @Override
    public List<T> creates(T registry) {
        return null;
    }
}
