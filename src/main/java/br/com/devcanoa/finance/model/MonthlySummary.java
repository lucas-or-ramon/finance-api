package br.com.devcanoa.finance.model;

import java.time.LocalDate;
import java.util.List;

public class MonthlySummary {
    private final LocalDate date;
    private final Double balance;
    private final Double totalRevenue;
    private final Double totalExpenditure;
    private final List<CategorySummary> categorySummaries;

    public MonthlySummary(LocalDate date, Double totalRevenue, Double totalExpenditure, Double balance, List<CategorySummary> categorySummaries) {
        this.date = date;
        this.balance = balance;
        this.totalRevenue = totalRevenue;
        this.totalExpenditure = totalExpenditure;
        this.categorySummaries = categorySummaries;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public Double getTotalExpenditure() {
        return totalExpenditure;
    }

    public List<CategorySummary> getCategorySummaries() {
        return List.copyOf(categorySummaries);
    }
}
