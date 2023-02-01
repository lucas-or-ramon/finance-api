package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.controller.response.MonthlyResumeResponse;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Revenue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthlyResumeService {

    private final FinanceService<Revenue> revenueService;
    private final FinanceService<Expenditure> expenditureService;

    public MonthlyResumeService(final FinanceService<Revenue> revenueService,
                                final FinanceService<Expenditure> expenditureService) {
        this.revenueService = revenueService;
        this.expenditureService = expenditureService;
    }

    public MonthlyResumeResponse getMonthlyResume(final LocalDate date) {
        final double totalRevenue = getTotalRevenue(date);
        final double totalExpenditure = getTotalExpenditure(date);
        final double balance = totalRevenue - totalExpenditure;
        return new MonthlyResumeResponse(date, balance, totalRevenue, totalExpenditure);
    }

    private double getTotalExpenditure(final LocalDate date) {
        final var expenditures = expenditureService.getByDate(date);
        return expenditures.stream().mapToDouble(Expenditure::getValue).sum();
    }

    private double getTotalRevenue(final LocalDate date) {
        final var revenues = revenueService.getByDate(date);
        return revenues.stream().mapToDouble(Revenue::getValue).sum();
    }
}















