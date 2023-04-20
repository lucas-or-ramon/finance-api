package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Installments;

import java.time.LocalDate;

public interface RecurrenceRules {

    default boolean isValid(final FinanceDate start, final FinanceDate end) {
        LocalDate startDate = LocalDate.of(start.year(), start.month(), 1);
        LocalDate endDate = LocalDate.of(end.year(), end.month(), 1);
        return startDate.isBefore(endDate);
    }

    default Installments getInstallments(final FinanceDate start, final FinanceDate end, final FinanceDate date) {
        return null;
    }
}
