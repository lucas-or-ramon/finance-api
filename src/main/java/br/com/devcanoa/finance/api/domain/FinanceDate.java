package br.com.devcanoa.finance.api.domain;

import java.time.LocalDate;

public final class FinanceDate {

    private FinanceDate() {
    }

    public static LocalDate getStartOfMonth(final LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    public static LocalDate getEndOfMonth(final LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth());
    }

    public static LocalDate getDateFrom(final int year, final int month) {
        return LocalDate.of(year, month, 1);
    }
}
