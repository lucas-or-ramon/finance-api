package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.domain.response.MonthlyResumeResponse;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Revenue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthlyResumeService {

    private final CreditCardService creditCardService;
    private final RegistryService<Revenue> revenueRegistryService;
    private final RegistryService<Expenditure> expenditureRegistryService;

    public MonthlyResumeService(CreditCardService creditCardService,
                                RegistryService<Revenue> revenueRegistryService,
                                RegistryService<Expenditure> expenditureRegistryService) {
        this.creditCardService = creditCardService;
        this.revenueRegistryService = revenueRegistryService;
        this.expenditureRegistryService = expenditureRegistryService;
    }

    public MonthlyResumeResponse getMonthlyResume(final LocalDate date) {
        final var revenue = revenueRegistryService.getResume(date);
        final var expenditure = expenditureRegistryService.getResume(date);
        final double balance = revenue.total() - expenditure.total();
        return new MonthlyResumeResponse(date, balance, revenue, expenditure);
    }
}
