package br.com.devcanoa.finance.api.domain.response;

import java.time.LocalDate;

public record MonthlyResumeResponse(LocalDate date, double balance, ResumeResponse revenue,
                                    ResumeResponse expenditure) {
    public double getTotalRevenue() {
        return revenue.total();
    }

    public double getTotalExpediture() {
        return expenditure.total();
    }
}
