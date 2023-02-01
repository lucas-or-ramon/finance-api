package br.com.devcanoa.finance.api.controller.response;

import java.time.LocalDate;

public record MonthlyResumeResponse(LocalDate date, double balance, double totalRevenue, double totalExpenditure) {
}
