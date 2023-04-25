package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.adapter.inbound.dto.Request;
import br.com.devcanoa.finance.api.domain.model.CreditCard;
import br.com.devcanoa.finance.api.domain.model.Either;
import br.com.devcanoa.finance.api.domain.model.Monthly;
import br.com.devcanoa.finance.api.domain.model.Registry;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import br.com.devcanoa.finance.api.domain.service.MonthlyService;
import br.com.devcanoa.finance.api.domain.service.RegistryService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonthlyServiceImpl implements MonthlyService {

    private final CreditCardService creditCardService;
    private final RegistryService revenueService;
    private final RegistryService expenditureService;

    public MonthlyServiceImpl(final CreditCardService creditCardService,
                              final RegistryService expenditureService,
                              final RegistryService revenueService) {
        this.creditCardService = creditCardService;
        this.revenueService = revenueService;
        this.expenditureService = expenditureService;
    }

    @Override
    public Monthly getMonthlyResume(final Request.FinanceDateDto date) {
        final var revenues = revenueService.getByDate(date);
        final var totalRevenue = revenues.stream().mapToDouble(Registry::getValue).sum();
        final var expenditures = expenditureService.getByDate(date);
        final var totalExpenditure = expenditures.stream().mapToDouble(Registry::getValue).sum();
        final var balance = totalRevenue - totalExpenditure;
        final var creditCards = getCreditCards(revenues, expenditures);
        return new Monthly(date.mapToDomain(), balance, totalRevenue, totalExpenditure, revenues, expenditures, creditCards);
    }

    private List<CreditCard> getCreditCards(List<Registry> revenues, List<Registry> expenditures) {
        return Stream.concat(revenues.stream(), expenditures.stream())
                .map(Registry::getCreditCardId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet())
                .parallelStream()
                .map(creditCardService::getById)
                .filter(Either::isSuccess)
                .map(Either::getSuccess)
                .toList();
    }
}
