package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.Revenue;

public class RevenueMapper implements RegistryMapper<Revenue> {

    @Override
    public Revenue mapToDomain(String id, RegistryRequest request, CreditCard creditCard) {
        return Revenue.builder()
                .id(id)
                .value(request.value())
                .description(request.description())
                .category(request.category())
                .creditCard(creditCard)
                .recurrence(RecurrenceMapper.mapToDomain(request.recurrence()))
                .build();
    }
}
