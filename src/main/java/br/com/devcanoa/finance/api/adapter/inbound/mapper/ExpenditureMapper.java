package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.model.Expenditure;

public class ExpenditureMapper implements RegistryMapper<Expenditure> {

    @Override
    public Expenditure mapToDomain(String id, RegistryRequest request) {
        return new Expenditure(
                id,
                request.date(),
                request.value(),
                request.description(),
                request.creditCardId(),
                RecurrencyMapper.mapToDomain(request.recurrency()));
    }
}
