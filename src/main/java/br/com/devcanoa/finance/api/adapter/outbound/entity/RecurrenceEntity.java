package br.com.devcanoa.finance.api.adapter.outbound.entity;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.recurrence.Recurrence;
import br.com.devcanoa.finance.api.domain.model.recurrence.RecurrenceType;

public record RecurrenceEntity(RecurrenceType type, int dueDate, int start, int end) {

    public static RecurrenceEntity mapToEntity(final Recurrence recurrence) {
        return new RecurrenceEntity(recurrence.type(), recurrence.dueDate(),
                recurrence.start().getNumericValue(), recurrence.end().getNumericValue());
    }

    public Recurrence mapToDomain() {
        return Recurrence.builder()
                .type(type)
                .dueDate(dueDate)
                .start(FinanceDate.fromNumericValue(start))
                .end(FinanceDate.fromNumericValue(end))
                .build();
    }
}
