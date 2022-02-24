package br.com.devcanoa.finance.service;

import br.com.devcanoa.finance.domain.AnnualSummary;
import br.com.devcanoa.finance.domain.CategorySummary;
import br.com.devcanoa.finance.domain.MonthlySummary;
import br.com.devcanoa.finance.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SummaryService {

    private final TaskExecutor taskExecutor;
    private final RevenueService revenueService;
    private final ExpenditureService expenditureService;

    @Autowired
    public SummaryService(TaskExecutor taskExecutor, RevenueService revenueService, ExpenditureService expenditureService) {
        this.taskExecutor = taskExecutor;
        this.revenueService = revenueService;
        this.expenditureService = expenditureService;
    }

    public MonthlySummary getMonthlySummary(LocalDate date) {
        List<Revenue> revenues = revenueService.getRevenuesByYearAndMonth(date);
        Double totalRevenue = revenues.stream().mapToDouble(Revenue::getValue).sum();

        List<Expenditure> expenditures = expenditureService.getExpendituresByYearAndMonth(date);
        Double totalExpenditure = expenditures.stream().mapToDouble(Expenditure::getValue).sum();

        List<CategorySummary> categorySummaries = getCategorySummaries(expenditures);
        Double balance = totalRevenue - totalExpenditure;

        return new MonthlySummary(date, totalRevenue, totalExpenditure, balance, categorySummaries);
    }

    private List<CategorySummary> getCategorySummaries(List<Expenditure> expenditures) {
        List<CategorySummary> categorySummaries = new ArrayList<>();

        expenditures.stream().collect(Collectors.groupingBy(Expenditure::getCategory)).forEach((key, value) ->
                categorySummaries.add(new CategorySummary(key.getValue(), value.stream().mapToDouble(Expenditure::getValue).sum())));

        return categorySummaries;
    }

    public AnnualSummary getAnnualSummary(LocalDate dateFrom) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        AnnualSummary annualSummary = new AnnualSummary();

        IntStream.rangeClosed(0, 11).forEach(i ->
                futures.add(CompletableFuture.runAsync(() -> annualSummary.add(getMonthlySummary(dateFrom.minusMonths(i))), taskExecutor)
                        .completeOnTimeout(null, 1000, TimeUnit.MILLISECONDS)));

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();
        } catch (Exception e) {
        }

        annualSummary.setAnnualCategoriesSummary();

        return annualSummary;
    }
}















