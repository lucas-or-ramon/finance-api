package br.com.devcanoa.finance.api.domain.response;

import java.util.List;

public final class AnnualResumeResponse {

    private final double balance;
    private final double totalRevenue;
    private final double totalExpenditure;
    private final List<MonthlyResumeResponse> monthlyResumes;

    public AnnualResumeResponse(final List<MonthlyResumeResponse> monthlyResumes) {
        this.monthlyResumes = monthlyResumes;
        totalRevenue = monthlyResumes.stream().mapToDouble(MonthlyResumeResponse::getTotalRevenue).sum();
        totalExpenditure = monthlyResumes.stream().mapToDouble(MonthlyResumeResponse::getTotalExpediture).sum();
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

    public List<MonthlyResumeResponse> getMonthlyResumes() {
        return monthlyResumes;
    }
}
