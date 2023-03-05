package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import br.com.devcanoa.finance.api.domain.model.Monthly;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class AnnualResponse {

    private final double balance;
    private final double totalRevenue;
    private final double totalExpenditure;
    private final List<Monthly> monthlyResumes;

    public AnnualResponse(final List<Monthly> monthlyResumes) {
        this.monthlyResumes = monthlyResumes;
        totalRevenue = monthlyResumes.stream().mapToDouble(Monthly::totalRevenue).sum();
        totalExpenditure = monthlyResumes.stream().mapToDouble(Monthly::totalExpenditure).sum();
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

    public List<Monthly> getMonthlyResumes() {
        return monthlyResumes;
    }
}
