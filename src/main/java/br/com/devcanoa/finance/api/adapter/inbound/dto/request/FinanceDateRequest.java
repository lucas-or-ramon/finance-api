package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record FinanceDateRequest(@Min(2000) @Max(9999) int year, @Min(1) @Max(12) int month) {

    public FinanceDate mapToDomain() {
        return new FinanceDate(year, month);
    }
}
