package br.com.devcanoa.finance.api.domain.dto.response;

import java.time.LocalDate;

public record MonthlyResponse(LocalDate date, double balance, ResumeResponse revenue,
                              ResumeResponse expenditure) {
    public double getTotalRevenue() {
        return revenue.total();
    }

    public double getTotalExpediture() {
        return expenditure.total();
    }
}
