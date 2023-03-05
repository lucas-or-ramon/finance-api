package br.com.devcanoa.finance.api.domain.model.recurrency;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;

import java.util.List;

public interface RecurrencyRules<T extends RegistryEntity> {

    List<T> creates(T registry);
}
