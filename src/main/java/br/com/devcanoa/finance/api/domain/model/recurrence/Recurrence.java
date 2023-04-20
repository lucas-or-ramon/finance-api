package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Installments;

public record Recurrence(RecurrenceType type, int dueDate, FinanceDate start, FinanceDate end) {

    public boolean isValid() {
        return type.isValid(start, end);
    }

    public Installments getInstallments(final FinanceDate date) {
        return type.getInstallments(start, end, date);
    }
}
