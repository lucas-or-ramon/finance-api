package br.com.devcanoa.finance.api.adapter.outbound.mapper;

import br.com.devcanoa.finance.api.adapter.outbound.entity.RegistryEntity;
import br.com.devcanoa.finance.api.domain.model.Registry;

public interface RegistryEntityMapper<R extends RegistryEntity, T extends Registry> {

    T mapToDomain(final R entity);

    R mapToEntity(final T registry);
}
