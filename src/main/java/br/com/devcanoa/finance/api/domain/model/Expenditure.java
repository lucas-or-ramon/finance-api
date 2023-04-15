package br.com.devcanoa.finance.api.domain.model;

import java.time.LocalDate;

public class Expenditure extends Registry {

    public Expenditure(final String id, final LocalDate date, final Double value, final String description, final String creditCardId, final Recurrency recurrency) {
        super(id, date, value, description, creditCardId, recurrency);
    }
}
