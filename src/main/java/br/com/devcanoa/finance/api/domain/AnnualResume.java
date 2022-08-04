package br.com.devcanoa.finance.api.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class AnnualResume {
    private double balance;
    private double totalRevenue;
    private double totalExpenditure;
    private final List<MonthlyResume> monthlyResumeList;

    private AnnualResume() {
        this.monthlyResumeList = new ArrayList<>();
    }

    public static AnnualResume builder() {
        return new AnnualResume();
    }

    public AnnualResume add(MonthlyResume monthlyResume) {
        this.monthlyResumeList.add(monthlyResume);
        return this;
    }

    public AnnualResume build() {
        this.totalRevenue = monthlyResumeList.stream().mapToDouble(MonthlyResume::getTotalRevenue).sum();
        this.totalExpenditure = monthlyResumeList.stream().mapToDouble(MonthlyResume::getTotalExpenditure).sum();
        this.balance = totalRevenue - totalExpenditure;
        return this;
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

    public List<MonthlyResume> getMonthlyResumeList() {
        monthlyResumeList.sort(Comparator.comparing(MonthlyResume::getDate));
        return monthlyResumeList;
    }
}
