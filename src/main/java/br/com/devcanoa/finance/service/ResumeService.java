package br.com.devcanoa.finance.service;

import br.com.devcanoa.finance.domain.AnnualResume;
import br.com.devcanoa.finance.domain.CategoryResume;
import br.com.devcanoa.finance.domain.MonthlyResume;
import br.com.devcanoa.finance.model.Expenditure;
import br.com.devcanoa.finance.model.Revenue;
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
        Double totalRevenue = revenues.stream().mapToDouble(Revenue::getValue).sum();

        List<Expenditure> expenditures = expenditureService.getExpendituresByYearAndMonth(date);
        Double totalExpenditure = expenditures.stream().mapToDouble(Expenditure::getValue).sum();

        List<CategoryResume> categoryResumeList = getCategoryResume(expenditures);
        Double balance = totalRevenue - totalExpenditure;

        return new MonthlyResume(date, totalRevenue, totalExpenditure, balance, categoryResumeList);
    }

    private List<CategoryResume> getCategoryResume(List<Expenditure> expenditures) {
        List<CategoryResume> categoryResumeList = new ArrayList<>();

        expenditures.stream().collect(Collectors.groupingBy(Expenditure::getCategory)).forEach((key, value) ->
                categoryResumeList.add(new CategoryResume(key.getValue(), value.stream().mapToDouble(Expenditure::getValue).sum())));

        return categoryResumeList;
    }

    public AnnualResume getAnnualResume(LocalDate date) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        AnnualResume annualResume = new AnnualResume();

        IntStream.rangeClosed(0, 11).forEach(i ->
                futures.add(CompletableFuture.runAsync(() -> annualResume.add(getMonthlyResume(date.minusMonths(i))), taskExecutor)
                        .completeOnTimeout(null, 1000, TimeUnit.MILLISECONDS)));

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();
        } catch (Exception e) {
        }

        annualResume.setAnnualCategoriesResume();

        return annualResume;
    }
}















