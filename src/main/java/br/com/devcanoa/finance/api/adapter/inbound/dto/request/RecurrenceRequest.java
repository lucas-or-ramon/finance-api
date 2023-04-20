package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.recurrence.RecurrenceType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record RecurrenceRequest(@NotNull RecurrenceType type,
                                @Min(1) @Max(31) int dueDate,
                                @NotNull FinanceDate start,
                                @NotNull FinanceDate end) {
}
