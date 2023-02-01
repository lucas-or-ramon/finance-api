package br.com.devcanoa.finance.api.controller.response;

import java.util.Comparator;
import java.util.List;

public final class AnnualResumeResponse {

    private final double balance;
    private final double totalRevenue;
    private final double totalExpenditure;
    private final List<MonthlyResumeResponse> monthlyResumeResponseList;

    public AnnualResumeResponse(final List<MonthlyResumeResponse> monthlyResumeResponseList) {
        this.monthlyResumeResponseList = monthlyResumeResponseList;
        totalRevenue = monthlyResumeResponseList.stream().mapToDouble(MonthlyResumeResponse::totalRevenue).sum();
        totalExpenditure = monthlyResumeResponseList.stream().mapToDouble(MonthlyResumeResponse::totalExpenditure).sum();
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

    public List<MonthlyResumeResponse> getMonthlyResumeList() {
        monthlyResumeResponseList.sort(Comparator.comparing(MonthlyResumeResponse::date));
        return monthlyResumeResponseList;
    }
}
