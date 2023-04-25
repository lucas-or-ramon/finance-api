package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;

public enum RecurrenceType {
    FIXED(new RecurrenceRule.Fixed()),
    PARCEL(new RecurrenceRule.Parcel()),
    ONCE(new RecurrenceRule.Once());

    private final RecurrenceRule rules;

    RecurrenceType(RecurrenceRule rules) {
        this.rules = rules;
    }

    public boolean isValid(final FinanceDate start, final FinanceDate end) {
        return rules.isValid(start, end);
    }

    public Installments getInstallments(final FinanceDate start, final FinanceDate end, final FinanceDate date) {
        return rules.getInstallments(start, end, date);
    }
}
