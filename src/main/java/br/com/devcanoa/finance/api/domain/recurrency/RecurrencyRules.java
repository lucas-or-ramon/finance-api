package br.com.devcanoa.finance.api.domain.recurrency;

import br.com.devcanoa.finance.api.infra.adapter.entity.Registry;

import java.util.List;

public interface RecurrencyRules<T extends Registry> {

    List<T> creates(T registry);
}
