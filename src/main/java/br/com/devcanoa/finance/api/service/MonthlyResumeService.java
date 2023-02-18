package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.controller.response.MonthlyResumeResponse;
import br.com.devcanoa.finance.api.controller.response.RegistryResponseMapper;
import br.com.devcanoa.finance.api.controller.response.ResumeResponse;
import br.com.devcanoa.finance.api.controller.response.ResumeResponseMapper;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Revenue;
import br.com.devcanoa.finance.api.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthlyResumeService {

    private final MongoRepository<Revenue> revenueRepository;
    private final MongoRepository<Expenditure> expenditureRepository;

    public MonthlyResumeService(final MongoRepository<Revenue> revenueRepository,
                                final MongoRepository<Expenditure> expenditureRepository) {
        this.revenueRepository = revenueRepository;
        this.expenditureRepository = expenditureRepository;
    }

    public MonthlyResumeResponse getMonthlyResume(final LocalDate date) {
        final var revenue = getTotalRevenue(date);
        final var expenditure = getTotalExpenditure(date);
        final double balance = revenue.total() - expenditure.total();
        return new MonthlyResumeResponse(date, balance, revenue, expenditure);
    }

    private ResumeResponse getTotalRevenue(final LocalDate date) {
        return new ResumeResponseMapper<Revenue>(new RegistryResponseMapper<>()).apply(revenueRepository.getByDate(date));
    }

    private ResumeResponse getTotalExpenditure(final LocalDate date) {
        return new ResumeResponseMapper<Expenditure>(new RegistryResponseMapper<>()).apply(expenditureRepository.getByDate(date));
    }
}
