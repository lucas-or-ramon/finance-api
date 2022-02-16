package br.com.devcanoa.finance.model;

import java.util.ArrayList;
import java.util.List;

public class AnnualSummary {
    private List<MonthlySummary> monthlySummaries = new ArrayList<>();
    private List<CategorySummary> annualCategoriesSummary = new ArrayList<>();

    public List<MonthlySummary> getMonthlySummaries() {
        return monthlySummaries;
    }

    public List<CategorySummary> getAnnualCategoriesSummary() {
        return annualCategoriesSummary;
    }

    public void add(MonthlySummary monthlySummary) {
        this.monthlySummaries.add(monthlySummary);
    }

    public void add(CategorySummary categorySummary) {
        this.annualCategoriesSummary.add(categorySummary);
    }
}
