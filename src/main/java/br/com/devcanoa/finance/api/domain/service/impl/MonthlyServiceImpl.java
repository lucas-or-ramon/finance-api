package br.com.devcanoa.finance.api.domain.service.impl;

import br.com.devcanoa.finance.api.domain.model.*;
import br.com.devcanoa.finance.api.domain.service.CreditCardService;
import br.com.devcanoa.finance.api.domain.service.MonthlyService;
import br.com.devcanoa.finance.api.domain.service.RegistryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonthlyServiceImpl implements MonthlyService {

    private final CreditCardService creditCardService;
    private final RegistryService<Revenue> revenueService;
    private final RegistryService<Expenditure> expenditureService;

    public MonthlyServiceImpl(final CreditCardService creditCardService,
                              final RegistryService<Expenditure> expenditureService,
                              final RegistryService<Revenue> revenueService) {
        this.creditCardService = creditCardService;
        this.revenueService = revenueService;
        this.expenditureService = expenditureService;
    }

    @Override
    public Monthly getMonthlyResume(final LocalDate date) {
        final var revenues = revenueService.getByDate(date);
        final var totalRevenue = revenues.stream().mapToDouble(Revenue::getValue).sum();
        final var expenditures = expenditureService.getByDate(date);
        final var totalExpenditure = expenditures.stream().mapToDouble(Expenditure::getValue).sum();
        final var balance = totalRevenue - totalExpenditure;
        final var creditCards = getCreditCards(revenues, expenditures);
        return new Monthly(date, balance, totalRevenue, totalExpenditure, revenues, expenditures, creditCards);
    }

    private List<CreditCard> getCreditCards(List<Revenue> revenues, List<Expenditure> expenditures) {
        return Stream.concat(revenues.stream(), expenditures.stream())
                .map(Registry::getCreditCardId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet())
                .parallelStream()
                .map(creditCardService::getById)
                .toList();
    }
}
