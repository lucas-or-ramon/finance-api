package br.com.devcanoa.finance.api.domain.model;

import java.util.List;

public record Monthly(FinanceDate date, double balance, double totalRevenue, double totalExpenditure,
                      List<Registry> revenues, List<Registry> expenditures, List<CreditCard> creditCards) {
}
