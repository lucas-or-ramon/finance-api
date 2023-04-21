package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.model.Revenue;

public class RevenueMapper implements RegistryMapper<Revenue> {

    @Override
    public Revenue mapToDomain(String id, RegistryRequest request) {
        return new Revenue(
                id,
                request.value(),
                request.description(),
                request.creditCardId(),
                RecurrenceMapper.mapToDomain(request.recurrence()));
    }
}
