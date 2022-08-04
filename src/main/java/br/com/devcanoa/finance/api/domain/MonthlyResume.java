package br.com.devcanoa.finance.api.domain;

import java.time.LocalDate;

public class MonthlyResume {
    private final LocalDate date;
    private double balance;
    private double totalRevenue;
    private double totalExpenditure;

    private MonthlyResume(LocalDate date) {
        this.date = date;
    }

    public static MonthlyResume builder(LocalDate localDate) {
        return new MonthlyResume(localDate);
    }

    public MonthlyResume addBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public MonthlyResume addTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
        return this;
    }

    public MonthlyResume addTotalExpenditure(double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
        return this;
    }

    public MonthlyResume build() {
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getBalance() {
        return balance;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalExpenditure() {
        return totalExpenditure;
    }
}
