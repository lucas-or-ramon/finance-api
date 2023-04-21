package br.com.devcanoa.finance.api.domain.model;

import java.time.LocalDate;

public record FinanceDate(int year, int month) {

    public static FinanceDate fromNumericValue(int numericValue) {
        final int year = ((numericValue - 1) / 12) + 1;
        final int month = ((numericValue - 1) % 12) + 1;
        return new FinanceDate(year, month);
    }

    public static FinanceDate now() {
        final LocalDate now = LocalDate.now();
        return new FinanceDate(now.getYear(), now.getMonthValue());
    }

    public FinanceDate minusMonths(final int i) {
        return (month - i < 1)
                ? new FinanceDate(year - 1, 12 - (i - month))
                : new FinanceDate(year, month - i);
    }

    public int monthsBetween(final FinanceDate end) {
        return (end.year() - year) * 12 + (end.month() - month) + 1;
    }

    public int getNumericValue() {
        return ((year - 1) * 12) + month;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof FinanceDate other)) return false;
        return other.year() == year && other.month() == month;
    }
}
