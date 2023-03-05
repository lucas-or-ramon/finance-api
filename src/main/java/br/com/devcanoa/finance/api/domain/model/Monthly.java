package br.com.devcanoa.finance.api.domain.model;

import java.time.LocalDate;
import java.util.List;

public record Monthly(LocalDate date, double balance, double totalRevenue, double totalExpenditure,
                      List<Revenue> revenues, List<Expenditure> expenditures, List<CreditCard> creditCards) {
}
