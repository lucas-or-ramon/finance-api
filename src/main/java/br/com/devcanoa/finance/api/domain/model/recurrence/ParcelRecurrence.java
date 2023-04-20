package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;
import br.com.devcanoa.finance.api.domain.model.Installments;

public class ParcelRecurrence implements RecurrenceRules {

    @Override
    public Installments getInstallments(final FinanceDate start, final FinanceDate end, final FinanceDate date) {
        final int total = start.monthsBetween(end);
        final int paid = start.monthsBetween(date);
        return new Installments(paid, total);
    }
}
