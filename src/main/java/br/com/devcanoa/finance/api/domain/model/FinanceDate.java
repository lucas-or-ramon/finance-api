package br.com.devcanoa.finance.api.domain.model;

import java.time.LocalDate;

public record FinanceDate(int year, int month) {

    public static FinanceDate fromNumericValue(int numericValue) {
        if (numericValue < 1) {
            throw new IllegalArgumentException("numericValue must be greater than zero");
        }
        final int year = ((numericValue - 1) / 12) + 1;
        final int month = ((numericValue - 1) % 12) + 1;
        return new FinanceDate(year, month);
    }

    public static FinanceDate now() {
        final LocalDate now = LocalDate.now();
        return new FinanceDate(now.getYear(), now.getMonthValue());
    }

    public FinanceDate minusMonths(final int i) {
        if (i < 0 || i > 11) {
            throw new IllegalArgumentException("i must be between 0 and 11");
        }
        return (month - i < 1)
                ? new FinanceDate(year - 1, 12 - (i - month))
                : new FinanceDate(year, month - i);
    }

    public int monthsBetween(final FinanceDate end) {
        if (end.getNumericValue() < getNumericValue()) {
            throw new IllegalArgumentException("end must be greater than this");
        }
        return (end.year() - year) * 12 + (end.month() - month) + 1;
    }

    public int getNumericValue() {
        if (year < 1 || month < 1 || month > 12) {
            throw new IllegalArgumentException("year and month must be greater than zero");
        }
        return ((year - 1) * 12) + month;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof FinanceDate other)) return false;
        return other.year() == year && other.month() == month;
    }
}
