package br.com.devcanoa.finance.api.adapter.inbound.mapper;

import br.com.devcanoa.finance.api.adapter.inbound.dto.response.MonthlyResponse;
import br.com.devcanoa.finance.api.domain.model.Expenditure;
import br.com.devcanoa.finance.api.domain.model.Monthly;
import br.com.devcanoa.finance.api.domain.model.Revenue;

public class MonthlyMapper {

    private final RegistryMapper<Revenue> revenueMapper;
    private final RegistryMapper<Expenditure> expenditureMapper;

    public MonthlyMapper(RegistryMapper<Revenue> revenueMapper, RegistryMapper<Expenditure> expenditureMapper) {
        this.revenueMapper = revenueMapper;
        this.expenditureMapper = expenditureMapper;
    }

    public MonthlyResponse mapToResponse(final Monthly monthly) {
        return new MonthlyResponse(
                monthly.date(),
                monthly.balance(),
                monthly.totalRevenue(),
                monthly.totalExpenditure(),
                monthly.revenues().stream().map(revenueMapper::mapToResponse).toList(),
                monthly.expenditures().stream().map(expenditureMapper::mapToResponse).toList(),
                monthly.creditCards().stream().map(CreditCardMapper::mapToResponse).toList());
    }
}
