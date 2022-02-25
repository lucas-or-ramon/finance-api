package br.com.devcanoa.finance.domain;

import java.time.LocalDate;

public class FinanceDate {
    public static LocalDate getStartOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }

    public static LocalDate getEndOfMonth(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth());
    }

    public static LocalDate getDateFrom(int year, int month) {
        return LocalDate.of(year, month, 1);
    }
}
