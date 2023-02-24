package br.com.devcanoa.finance.api.domain.dto.response;

import java.util.List;

public final class AnnualResponse {

    private final double balance;
    private final double totalRevenue;
    private final double totalExpenditure;
    private final List<MonthlyResponse> monthlyResumes;

    public AnnualResponse(final List<MonthlyResponse> monthlyResumes) {
        this.monthlyResumes = monthlyResumes;
        totalRevenue = monthlyResumes.stream().mapToDouble(MonthlyResponse::getTotalRevenue).sum();
        totalExpenditure = monthlyResumes.stream().mapToDouble(MonthlyResponse::getTotalExpediture).sum();
        balance = totalRevenue - totalExpenditure;
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

    public List<MonthlyResponse> getMonthlyResumes() {
        return monthlyResumes;
    }
}
