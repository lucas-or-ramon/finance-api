package br.com.devcanoa.finance.api.domain.model.recurrence;

import br.com.devcanoa.finance.api.domain.exception.RecurrenceException;
import br.com.devcanoa.finance.api.domain.model.FinanceDate;

import java.time.LocalDate;

public interface RecurrenceRule {

    default boolean isValid(final FinanceDate start, final FinanceDate end) {
        LocalDate startDate = LocalDate.of(start.year(), start.month(), 1);
        LocalDate endDate = LocalDate.of(end.year(), end.month(), 1);
        return startDate.isBefore(endDate);
    }

    default Installments getInstallments(final FinanceDate start, final FinanceDate end, final FinanceDate date) {
        return null;
    }

    final class Fixed implements RecurrenceRule {}

    final class Once implements RecurrenceRule {
        @Override
        public boolean isValid(final FinanceDate start, final FinanceDate end) {
            return start.equals(end);
        }
    }

    final class Parcel implements RecurrenceRule {
        @Override
        public Installments getInstallments(final FinanceDate start, final FinanceDate end, final FinanceDate date) {
            if (date == null) {
                throw new RecurrenceException("Date is required");
            }
            final int total = start.monthsBetween(end);
            final int paid = start.monthsBetween(date);
            return new Installments(paid, total);
        }
    }
}
