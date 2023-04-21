package br.com.devcanoa.finance.api.adapter.inbound.dto.request;

import br.com.devcanoa.finance.api.domain.model.recurrence.RecurrenceType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RecurrenceRequest(@NotNull RecurrenceType type,
                                @Min(1) @Max(31) int dueDate,
                                @NotNull FinanceDateRequest start,
                                @NotNull FinanceDateRequest end) {
}
