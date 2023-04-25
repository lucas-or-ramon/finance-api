package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.exception.RecurrenceException;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;

public final class Recurrence {
    private final int dueDate;
    private final FinanceDate end;
    private final FinanceDate start;
    private final RecurrenceType type;
    private final Installments installments;

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(final FinanceDate date) {
        return new Builder(date);
    }

    public RecurrenceType type() {
        return type;
    }

    public int dueDate() {
        return dueDate;
    }

    public FinanceDate start() {
        return start;
    }

    public FinanceDate end() {
        return end;
    }

    public Installments installments() {
        return installments;
    }

    public boolean isValid() {
        return type.isValid(start, end);
    }


    public static class Builder {
        private int dueDate;
        private FinanceDate end;
        private FinanceDate date;
        private FinanceDate start;
        private RecurrenceType type;

        private Builder() {}

        private Builder(final FinanceDate date) {
            this.date = date;
        }

        public Builder dueDate(final int dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder end(final FinanceDate end) {
            this.end = end;
            return this;
        }

        public Builder start(final FinanceDate start) {
            this.start = start;
            return this;
        }

        public Builder type(final RecurrenceType type) {
            this.type = type;
            return this;
        }

        public Recurrence build() {
            if (type == null || start == null || end == null || dueDate < 1 || dueDate > 31) {
                throw new RecurrenceException("Invalid recurrence");
            }

            if (!type.isValid(start, end)) {
                throw new RecurrenceException("Invalid recurrence");
            }

            final var installments = type.getInstallments(start, end, date);
            return new Recurrence(type, dueDate, start, end, installments);
        }
    }

    private Recurrence(RecurrenceType type, int dueDate, FinanceDate start, FinanceDate end, Installments installments) {
        this.end = end;
        this.type = type;
        this.start = start;
        this.dueDate = dueDate;
        this.installments = installments;
    }
}
