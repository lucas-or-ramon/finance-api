package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MonthlyResponse(LocalDate date, double balance, double totalRevenue, double totalExpenditure,
                              List<RegistryResponse> revenues, List<RegistryResponse> expenditures,
                              List<CreditCardResponse> creditCards) {
}
