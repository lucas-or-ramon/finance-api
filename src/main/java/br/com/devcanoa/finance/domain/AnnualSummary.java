package br.com.devcanoa.finance.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnnualSummary {
    private final List<MonthlySummary> monthlySummaries = new ArrayList<>();
    private final List<CategorySummary> annualCategoriesSummary = new ArrayList<>();

    public void add(MonthlySummary monthlySummary) {
        this.monthlySummaries.add(monthlySummary);
    }

    private void add(CategorySummary categorySummary) {
        this.annualCategoriesSummary.add(categorySummary);
    }

    public void setAnnualCategoriesSummary() {
        this.monthlySummaries.stream()
                .flatMap(monthlySummary -> monthlySummary.getCategorySummaries().stream())
                .collect(Collectors.groupingBy(CategorySummary::getCategory))
                .forEach((key, value) -> add(new CategorySummary(key, value.stream().mapToDouble(CategorySummary::getTotal).sum())));
    }

    public List<MonthlySummary> getMonthlySummaries() {
        return monthlySummaries;
    }

    public List<CategorySummary> getAnnualCategoriesSummary() {
        return annualCategoriesSummary;
    }
}
