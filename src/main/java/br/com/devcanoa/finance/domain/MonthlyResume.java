package br.com.devcanoa.finance.domain;

import java.time.LocalDate;
import java.util.List;

public class MonthlyResume {
    private final LocalDate date;
    private final Double balance;
    private final Double totalRevenue;
    private final Double totalExpenditure;
    private final List<CategoryResume> categoryResume;

    public MonthlyResume(LocalDate date, Double totalRevenue, Double totalExpenditure, Double balance, List<CategoryResume> categoryResume) {
        this.date = date;
        this.balance = balance;
        this.totalRevenue = totalRevenue;
        this.categoryResume = categoryResume;
        this.totalExpenditure = totalExpenditure;
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

    public List<CategoryResume> getCategoryResume() {
        return List.copyOf(categoryResume);
    }
}
