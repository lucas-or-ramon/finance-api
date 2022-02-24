package br.com.devcanoa.finance.domain;

public class FinanceDate {
    private final int year;
    private final int month;

    public FinanceDate(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
