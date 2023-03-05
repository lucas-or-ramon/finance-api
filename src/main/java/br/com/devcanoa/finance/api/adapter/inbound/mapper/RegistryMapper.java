package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.RegistryResponse;
import br.com.devcanoa.finance.api.domain.model.Registry;

import java.util.Objects;

public interface RegistryMapper<T extends Registry> {

    T mapToDomain(String id, RegistryRequest request);

    default RegistryResponse mapToResponse(final T registry) {
        return new RegistryResponse(
                registry.getId(),
                registry.getDate(),
                registry.getValue(),
                registry.getDescription(),
                registry.getCreditCardId(),
                RecurrencyMapper.mapToResponse(registry.getRecurrency())
        );
    }
}
