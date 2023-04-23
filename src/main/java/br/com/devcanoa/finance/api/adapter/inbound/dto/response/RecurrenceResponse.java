package br.com.devcanoa.finance.api.adapter.inbound.dto.response;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.recurrence.Installments;
import br.com.devcanoa.finance.api.domain.model.recurrence.RecurrenceType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecurrenceResponse(RecurrenceType type, int dueDate, FinanceDate start, FinanceDate end,
                                 Installments installments) {
}
