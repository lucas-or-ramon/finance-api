package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.controller.response.MonthlyResumeResponse;
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
        final double totalRevenue = getTotalRevenue(date);
        final double totalExpenditure = getTotalExpenditure(date);
        final double balance = totalRevenue - totalExpenditure;
        return new MonthlyResumeResponse(date, balance, totalRevenue, totalExpenditure);
    }

    private double getTotalExpenditure(final LocalDate date) {
        final var expenditures = expenditureRepository.getByDate(date);
        return expenditures.stream().mapToDouble(Expenditure::getValue).sum();
    }

    private double getTotalRevenue(final LocalDate date) {
        final var revenues = revenueRepository.getByDate(date);
        return revenues.stream().mapToDouble(Revenue::getValue).sum();
    }
}















