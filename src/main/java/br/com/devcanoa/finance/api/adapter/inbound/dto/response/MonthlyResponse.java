package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MonthlyResponse(FinanceDate date, double balance, double totalRevenue, double totalExpenditure,
                              List<RegistryResponse> revenues, List<RegistryResponse> expenditures,
                              List<CreditCardResponse> creditCards) {
}
