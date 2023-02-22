package br.com.devcanoa.finance.api.controller.response;

import java.time.LocalDate;
import java.util.List;

public record MonthlyResumeResponse(LocalDate date, double balance, ResumeResponse revenue,
                                    ResumeResponse expenditure) {
    public double getTotalRevenue() {
        return revenue.total();
    }

    public double getTotalExpediture() {
        return expenditure.total();
    }
}
