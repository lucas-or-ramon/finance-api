package br.com.devcanoa.finance.api.domain.adapter.service;

import br.com.devcanoa.finance.api.domain.dto.response.MonthlyResponse;
import br.com.devcanoa.finance.api.domain.port.service.MonthlyResumeService;
import br.com.devcanoa.finance.api.domain.port.service.RegistryService;

import java.time.LocalDate;

public class MonthlyResumeServiceImpl implements MonthlyResumeService {

    private final RegistryService revenueService;
    private final RegistryService expenditureService;

    public MonthlyResumeServiceImpl(RegistryService revenueService,
                                    RegistryService expenditureService) {
        this.revenueService = revenueService;
        this.expenditureService = expenditureService;
    }

    @Override
    public MonthlyResponse getResume(final LocalDate date) {
        final var revenue = revenueService.getResume(date);
        final var expenditure = expenditureService.getResume(date);
        final double balance = revenue.total() - expenditure.total();
        return new MonthlyResponse(date, balance, revenue, expenditure);
    }
}
