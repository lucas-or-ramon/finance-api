package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.model.FinanceDate;

public enum RecurrenceType {
    FIXED(new FixedRecurrence()),
    PARCEL(new ParcelRecurrence()),
    ONCE(new OnceRecurrence());

    private final RecurrenceRules rules;

    RecurrenceType(RecurrenceRules rules) {
        this.rules = rules;
    }

    public boolean isValid(final FinanceDate start, final FinanceDate end) {
        return rules.isValid(start, end);
    }

    public Installments getInstallments(final FinanceDate start, final FinanceDate end, final FinanceDate date) {
        return rules.getInstallments(start, end, date);
    }
}
