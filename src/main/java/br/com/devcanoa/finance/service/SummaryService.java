package br.com.devcanoa.finance.service;

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
        Double totalRevenue = getTotalRevenuesByMonth(date);

        List<Expenditure> expenditures = getExpenditures(date);
        Double totalExpenditure = getTotalExpenditureByMonth(expenditures);

        Double balance = totalRevenue - totalExpenditure;

        List<CategorySummary> categorySummaries = getCategorySummaries(expenditures);

        return new MonthlySummary(date, totalRevenue, totalExpenditure, balance, categorySummaries);
    }

    private List<Expenditure> getExpenditures(LocalDate date) {
        return expenditureService.getExpendituresByYearAndMonth(date);
    }

    public Double getTotalRevenuesByMonth(LocalDate date) {
        List<Revenue> revenues = revenueService.getRevenuesByYearAndMonth(date);

        return revenues.stream().mapToDouble(Revenue::getValue).sum();
    }

    private Double getTotalExpenditureByMonth(List<Expenditure> expenditures) {
        return expenditures.stream().mapToDouble(Expenditure::getValue).sum();
    }

    private List<CategorySummary> getCategorySummaries(List<Expenditure> expenditures) {
        List<CategorySummary> categorySummaries = new ArrayList<>();

        expenditures.stream().collect(Collectors.groupingBy(Expenditure::getCategory))
                .forEach((key, value) -> categorySummaries.add(new CategorySummary(key.getValue(), value.stream().mapToDouble(Expenditure::getValue).sum())));

        return categorySummaries;
    }

    public AnnualSummary getAnnualSummary(LocalDate dateFrom) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        AnnualSummary annualSummary = new AnnualSummary();

        IntStream.rangeClosed(0, 11).forEach(integer ->
                futures.add(CompletableFuture.runAsync(() ->
                        annualSummary.add(getMonthlySummary(dateFrom.minusMonths(integer))), taskExecutor)
                        .completeOnTimeout(null, 1000, TimeUnit.MILLISECONDS)));

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();
        } catch (Exception e) {
        }

        setAnnualCategoriesSummary(annualSummary);

        return annualSummary;
    }

    private void setAnnualCategoriesSummary(AnnualSummary annualSummary) {
        annualSummary.getMonthlySummaries().stream()
                .map(MonthlySummary::getCategorySummaries)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(CategorySummary::getCategory))
                .forEach((key, value) -> annualSummary.add(new CategorySummary(key, value.stream().mapToDouble(CategorySummary::getTotal).sum())));
    }
}















