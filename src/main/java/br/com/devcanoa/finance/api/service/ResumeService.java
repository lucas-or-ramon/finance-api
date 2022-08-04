package br.com.devcanoa.finance.api.service;

import br.com.devcanoa.finance.api.domain.AnnualResume;
import br.com.devcanoa.finance.api.domain.MonthlyResume;
import br.com.devcanoa.finance.api.model.Expenditure;
import br.com.devcanoa.finance.api.model.Revenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class ResumeService {

    private final TaskExecutor taskExecutor;
    private final RevenueService revenueService;
    private final ExpenditureService expenditureService;

    @Autowired
    public ResumeService(TaskExecutor taskExecutor, RevenueService revenueService, ExpenditureService expenditureService) {
        this.taskExecutor = taskExecutor;
        this.revenueService = revenueService;
        this.expenditureService = expenditureService;
    }

    public MonthlyResume getMonthlyResume(LocalDate date) {
        List<Revenue> revenues = revenueService.getRevenuesByYearAndMonth(date);
        var totalRevenue = revenues.stream().mapToDouble(Revenue::getValue).sum();

        List<Expenditure> expenditures = expenditureService.getExpendituresByYearAndMonth(date);
        var totalExpenditure = expenditures.stream().mapToDouble(Expenditure::getValue).sum();

        var balance = totalRevenue - totalExpenditure;

        return MonthlyResume.builder(date)
                .addTotalRevenue(totalRevenue)
                .addTotalExpenditure(totalExpenditure)
                .addBalance(balance)
                .build();
    }

    public AnnualResume getAnnualResume(LocalDate date) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        var annualResume = AnnualResume.builder();

        IntStream.rangeClosed(0, 11).forEach(i ->
                futures.add(CompletableFuture.runAsync(() -> annualResume.add(getMonthlyResume(date.minusMonths(i))), taskExecutor)
                        .completeOnTimeout(null, 1000, TimeUnit.MILLISECONDS)));

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();
        } catch (Exception e) {
        }

        return annualResume.build();
    }
}















