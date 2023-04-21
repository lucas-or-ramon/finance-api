package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;

public class OnceRecurrence implements RecurrenceRules {

    @Override
    public boolean isValid(final FinanceDate start, final FinanceDate end) {
        return start.equals(end);
    }
}
