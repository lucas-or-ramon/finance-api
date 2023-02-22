package br.com.devcanoa.finance.api.domain.recurrency;

import br.com.devcanoa.finance.api.model.Registry;

import java.util.List;

public class OnceRecurrency<T extends Registry> implements RecurrencyRules<T> {

    @Override
    public List<T> creates(T registry) {
        return null;
    }
}
