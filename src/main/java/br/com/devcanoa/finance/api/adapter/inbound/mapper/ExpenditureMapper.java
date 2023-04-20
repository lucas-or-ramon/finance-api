package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.request.RegistryRequest;
import br.com.devcanoa.finance.api.domain.model.Expenditure;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;

public class ExpenditureMapper implements RegistryMapper<Expenditure> {

    @Override
    public Expenditure mapToDomain(String id, RegistryRequest request) {
        return new Expenditure(id, request.value(), request.description(), request.creditCardId(), RecurrenceMapper.mapToDomain(request.recurrence()));
    }
}
