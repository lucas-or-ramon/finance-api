package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.adapter.inbound.dto.response.RegistryResponse;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Registry;

public interface RegistryMapper<T extends Registry> {

    T mapToDomain(String id, RegistryRequest request, final CreditCard creditCard);

    default RegistryResponse mapToResponse(final T registry, final FinanceDate date) {
        return new RegistryResponse(
                registry.getId(),
                registry.getValue(),
                registry.getDescription(),
                registry.getCategory(),
                registry.getCreditCard(),
                RecurrenceMapper.mapToResponse(registry.getRecurrence(), date)
        );
    }
}
